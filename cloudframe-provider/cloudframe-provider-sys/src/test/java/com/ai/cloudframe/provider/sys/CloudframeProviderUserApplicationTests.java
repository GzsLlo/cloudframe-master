//package com.ai.cloudframe.provider.sys;
//
//import com.ai.cloudframe.provider.sys.service.IPermissionService;
//import com.ai.cloudframe.provider.sys.service.IRoleService;
//import com.ai.cloudframe.provider.sys.service.IUserService;
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.io.UnsupportedEncodingException;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = CloudframeProviderSysApplication.class)
//@EnableAutoConfiguration
//public class CloudframeProviderUserApplicationTests {
//
//	@Autowired
//	IRoleService roleService;
//
//	@Autowired
//	IPermissionService permissionService;
//
//	private MockMvc mockMvc;
//
//	@Autowired
//	private WebApplicationContext webApplicationContext;
//
//	@Before
//	public void setup() {
//		this.mockMvc = MockMvcBuilders.webAppContextSetup(
//						this.webApplicationContext).build();
//	}
//
//	@Test
//	public void contextLoads() {
//
//	}
//
//	@Test
//	public void testGetUserRoles() {
//		Assert.assertTrue(roleService.getUserRolesByName("admin").size() >= 0);
//	}
//
//	@Test
//	public  void testGetUserPerms(){
//		Assert.assertTrue(permissionService.getUserPerms("admin").size() >= 0);
//	}
//
//	@Test
//	public void mockGetUser() throws Exception {
//		RequestBuilder request = MockMvcRequestBuilders.post("/sys/user/admin/admin@123");
//		MvcResult mvcResult = mockMvc.perform(request).andReturn();
//		Assert.assertEquals(JSONObject.parseObject(mvcResult.getResponse().getContentAsString()).getString("code"), "0000");
//	}
//
//}
