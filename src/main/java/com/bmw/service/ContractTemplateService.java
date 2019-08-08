package com.bmw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bmw.model.ContractTemplate;

@Service
public class ContractTemplateService {
	@Autowired
	private List<ContractTemplate> contractTemplateList;

	public List<ContractTemplate> getContractTemplateList(String dealerId, String regionId, String groupId, String contractStatus) {

		return contractTemplateList;
	}

}
