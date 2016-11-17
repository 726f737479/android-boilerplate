<?xml version="1.0"?>
<recipe>
 
	<instantiate from="src/app_package/Contract.java.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${className?lower_case}/${className}Contract.java" />
	<instantiate from="src/app_package/MvpViewFragment.java.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${className?lower_case}/${className}Fragment.java" />
	<instantiate from="src/app_package/Presenter.java.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${className?lower_case}/${className}Presenter.java" />
	<instantiate from="src/app_package/Component.java.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${className?lower_case}/${className}Component.java" />
<instantiate from="res/layout/fragment_blank.xml.ftl"
                       to="${escapeXmlAttribute(resOut)}/layout/fragment_${classToResource(className)}.xml" />

	<open file="${srcOut}/${className?lower_case}/${className}Presenter.java"/>
</recipe>
