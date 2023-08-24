package com.demo.datatypes;

/**
 * Static record representing a specific tool object, which is fully constructed when instantiated
 * @param code Unique code string that identifies a specific tool
 * @param brand The manufacturer brand of this tool
 * @param type ToolType object that defines the name of this tool and information about pricing and exclusions by type
 */
public record Tool(String code, String brand, ToolType type) { }