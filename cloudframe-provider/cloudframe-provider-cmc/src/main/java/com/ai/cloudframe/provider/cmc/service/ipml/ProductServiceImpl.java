package com.ai.cloudframe.provider.cmc.service.ipml;

import com.ai.cloudframe.provider.api.sys.model.dto.GoodProductSettleDto;
import com.ai.cloudframe.provider.api.sys.model.dto.ProductDto;
import com.ai.cloudframe.provider.api.sys.model.vo.ProdIntroProdVo;
import com.ai.cloudframe.provider.api.sys.model.vo.ProductMutexVo;
import com.ai.cloudframe.provider.cmc.entity.IntroduceProduct;
import com.ai.cloudframe.provider.cmc.entity.Product;
import com.ai.cloudframe.provider.cmc.entity.ProductGood;
import com.ai.cloudframe.provider.cmc.entity.ProductMutex;
import com.ai.cloudframe.provider.cmc.mapper.*;
import com.ai.cloudframe.provider.cmc.service.IProductService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Automated procedures
 * @since 2019-07-16
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {



  @Autowired(required = false)
  private IntroduceProductMapper introduceProductMapper = null;

  @Autowired(required = false)
  private ProductMutexMapper productMutexMapper = null;

  @Autowired(required = false)
  private ProductMapper productMapper = null;


  @Autowired(required = false)
  private ApiMapper apiMapper = null;

  @Autowired(required = false)
  private ApplicationMapper applicationMapper = null;

  @Override
  public IPage<Product> selectProduct(Map map) {
    Long pageNum = ((Integer) map.get("page")).longValue();
    Long pageSize = ((Integer) map.get("pageSize")).longValue();
    Page<Product> page = new Page<>(pageNum, pageSize);
    return productMapper.selectProduct(page, map);
  }

  @Override
  public List<Product> selectOneProduct(Map map) {
    return productMapper.selectOneProduct(map);
  }

  @Override
  @Transactional(rollbackFor=Exception.class)
  public boolean newProduct(ProductDto productDto) {

    Product product = new Product();
    BeanUtils.copyProperties(productDto, product);
    UUID uuid = UUID.randomUUID();
    String uuidStr = uuid.toString();
    product.setProductId(uuidStr);

    ProductMutex productMutex = new ProductMutex();
    productMutex.setProductId1(product.getProductId());
    Date date2 = new Date();
    Instant instant2 = date2.toInstant();//An instantaneous point on the time-line.(时间线上的一个瞬时点。)
    ZoneId zoneId2 = ZoneId.systemDefault();//A time-zone ID, such as {@code Europe/Paris}.(时区)
    LocalDateTime localDateTime2 = instant2.atZone(zoneId2).toLocalDateTime();
    productMutex.setCreateDate(localDateTime2);
    List productId2List = productDto.getProductId2();
    for (int i = 0; i < productId2List.size(); i++) {
      productMutex.setProductId2(productId2List.get(i).toString());
      int flag2 = productMutexMapper.insert(productMutex);
      if (flag2 < 1) {
        return false;
      }
    }

    int flag = productMapper.insert(product);
    if (flag < 1) {
      return false;
    }

    IntroduceProduct introduceProduct = new IntroduceProduct();
    introduceProduct.setIntroduceProductId(product.getIntroduceProductId());
    introduceProduct.setProductId(uuidStr);
    int flag3 = introduceProductMapper.updateById(introduceProduct);
    if (flag3 < 1) {
      return false;
    }

    return true;
  }


  @Override
  public ProductMutexVo productMutexCheck(String productIds) {

    String[] prodIdArr = productIds.split(";");
    for (int i = 0; i < prodIdArr.length; i++) {
      String prodId = prodIdArr[i];
      List<ProductMutex> productMutexList = this.baseMapper.selectByProdIds(prodId);
      for (ProductMutex each : productMutexList) {
        String productId2 = each.getProductId2();
        //查询出的结果，在productIds中存在，则互斥
        for (int j = 0; j < prodIdArr.length; j++) {
          if (prodIdArr[j].equals(productId2)) {
            ProductMutexVo productMutexVo=new ProductMutexVo();
            BeanUtils.copyProperties(each, productMutexVo);

            Map map1=new HashMap();
            map1.put("productId",each.getProductId1());
            List<Product> list1=this.baseMapper.selectOneProduct(map1);
            if(list1.size()>0){
              productMutexVo.setProductName1(list1.get(0).getProductName());
            }

            Map map2=new HashMap();
            map2.put("productId",each.getProductId2());
            List<Product> list2=this.baseMapper.selectOneProduct(map2);
            if(list2.size()>0){
              productMutexVo.setProductName2(list2.get(0).getProductName());
            }

            return productMutexVo;
          }
        }
      }
    }
    return null;
  }

  @Override
  public IPage<ProdIntroProdVo> prodUniIntroProdQuery(ProdIntroProdVo prodIntroProdVo) {
    Long pageNum = (null != prodIntroProdVo.getPage()) ? Long.valueOf( prodIntroProdVo.getPage()) : null;
    Long pageSize = (null !=  prodIntroProdVo.getPageSize())
        ? Long.valueOf(prodIntroProdVo.getPageSize()) : null;

    Page<ProdIntroProdVo> page = new Page<>(pageNum, pageSize);

    return this.baseMapper.prodUniIntroQueryTable(page,prodIntroProdVo);
  }

  @Override
  public IPage<GoodProductSettleDto> selectGoodProductSettle(Map map) {

    Long pageNum = ((Integer) map.get("page")).longValue();
    Long pageSize = ((Integer) map.get("pageSize")).longValue();
    Page<Product> page = new Page<>(pageNum, pageSize);
    return productMapper.selectGoodProductSettle(page, map);
  }


}
