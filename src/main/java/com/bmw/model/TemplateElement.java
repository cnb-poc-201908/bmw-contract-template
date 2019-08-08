package com.bmw.model;

import java.util.List;

public class TemplateElement {
	private String label;
	private String type;
	private String value;
	private Integer order;
	private List<TemplateElement> subElements;
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public List<TemplateElement> getSubElements() {
		return subElements;
	}
	public void setSubElements(List<TemplateElement> subElements) {
		this.subElements = subElements;
	}


}
