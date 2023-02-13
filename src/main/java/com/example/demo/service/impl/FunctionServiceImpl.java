package com.example.demo.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.FunctionDAO;
import com.example.demo.dto.FunctionCategoryDTO;
import com.example.demo.dto.FunctionDTO;
import com.example.demo.dto.GroupFunctionDTO;
import com.example.demo.service.FunctionService;
import com.example.demo.util.BoUtil;
import com.example.demo.vo.FunctionCategoryVO;
import com.example.demo.vo.FunctionVO;
import com.example.demo.vo.GroupFunctionVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FunctionServiceImpl implements FunctionService {
	
	@Autowired
	FunctionDAO functionDAO;

	@Override
	public BoUtil getFunctionDetails(Long id) {
		BoUtil boUtil = new BoUtil();
		
		try {	
			FunctionDTO Function = functionDAO.getFunctionDetails(id);
			log.info(Function.toString());
			
			boUtil = BoUtil.getDefaultTrueBo();
			boUtil.setData(Function);
			
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return boUtil;
	}

	@Override
	public BoUtil getFunctionList(Long groupId) {
		BoUtil boUtil = new BoUtil();
		
		try {
			
			List<HashMap<String, Object>> list = functionDAO.getFunctionList(groupId);
			
			boUtil = BoUtil.getDefaultTrueBo();
			boUtil.setData(list);
			
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return boUtil;
	}

	@Override
	public BoUtil insertFunction(FunctionVO vo) {
		BoUtil boUtil = new BoUtil();
		
		try {
			FunctionDTO dto = FunctionDTO.buildFromVo(vo);
//			functionDAO.insertFunctionDtls(dto);
			functionDAO.insertFunction(dto);
			log.info(dto.toString());
			
			boUtil = BoUtil.getDefaultTrueBo();
			boUtil.setData(dto);
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return boUtil;
	}
	@Override
	public BoUtil getFunctionCategoryList() {
		BoUtil boUtil = new BoUtil();
		
		try {
			
			List<HashMap<String, Object>> list = functionDAO.getFunctionCategoryList();
			
			boUtil = BoUtil.getDefaultTrueBo();
			boUtil.setData(list);
			
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return boUtil;
	}

	@Override
	public BoUtil insertCategoryFunction(FunctionCategoryVO vo) {
		BoUtil boUtil = new BoUtil();
		
		try {
			FunctionCategoryDTO dto = FunctionCategoryDTO.buildFromVo(vo);
			dto.setStatus("A");
			functionDAO.insertFunctionCategory(dto);
			log.info(dto.toString());
			
			boUtil = BoUtil.getDefaultTrueBo();
			boUtil.setData(dto);
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return boUtil;
	}

	@Override
	public BoUtil deleteFunctionCategory(FunctionCategoryVO vo) {
		BoUtil boUtil = new BoUtil();
		
		try {
			FunctionCategoryDTO dto = FunctionCategoryDTO.buildFromVo(vo);
			dto.setStatus("N");
			log.info(dto.toString());
			functionDAO.updateFunctionCategory(dto);
			log.info(dto.toString());
			
			boUtil = BoUtil.getDefaultTrueBo();
			boUtil.setData(dto);
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		return boUtil;
	}
	
}
