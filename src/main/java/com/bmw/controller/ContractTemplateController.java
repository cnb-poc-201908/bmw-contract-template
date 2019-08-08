package com.bmw.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bmw.entity.response.RestResponse;
import com.bmw.model.ContractTemplate;
import com.bmw.service.ContractTemplateService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController("Contract template endpoints")
@RequestMapping("/contracts")
@Api(description = "合同模板管理接口")
public class ContractTemplateController {

	private static Logger logger = LoggerFactory.getLogger(ContractTemplateController.class);

	@Autowired
	private ContractTemplateService contractTemplateService;

	@GetMapping(value = "", produces = "application/json")
	@ApiOperation(value = "合同模板列表信息查询")
	public RestResponse<List<ContractTemplate>> getPreContract(
			@RequestParam(value = "dealerId", required = false) String dealerId,
			@RequestParam(value = "regionId", required = false) String regionId,
			@RequestParam(value = "groupId", required = false) String groupId,
			@RequestParam(value = "keyword", required = false) String keyword) {

		logger.info("get ungenerated contract list");
		List<ContractTemplate> contractTemplateList = new ArrayList<>();
		RestResponse<List<ContractTemplate>> response = new RestResponse<>();
		response.setData(contractTemplateList);
    	return response;
	}

}

