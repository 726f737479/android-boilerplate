<?xml version="1.0"?>
<recipe>
 
	<instantiate from="src/app_package/MvpViewAdapter.java.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${className}Adapter.java" />


	<instantiate from="res/item.xml.ftl"
                   to="${escapeXmlAttribute(resOut)}/layout/item_${classToResource(dataClassName)}.xml" />

	<open file="${srcOut}/${className}Adapter.java"/>
</recipe>
