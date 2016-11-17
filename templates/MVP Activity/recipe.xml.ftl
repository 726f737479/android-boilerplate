<?xml version="1.0"?>
<recipe>
 
	<mkdir at="${escapeXmlAttribute(srcOut)}/${className?lower_case}" />

	<merge from="res/app_package/manifest_strings.xml.ftl"
             to="${escapeXmlAttribute(resOut)}/values/strings.xml" />

	<merge from="src/app_package/AndroidManifest.xml.ftl"
             to="${escapeXmlAttribute(manifestOut)}/AndroidManifest.xml" />

	<instantiate from="src/app_package/Contract.java.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${className?lower_case}/${className}Contract.java" />

	<instantiate from="src/app_package/Activity.java.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${className?lower_case}/${className}Activity.java" />

	<instantiate from="src/app_package/Component.java.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${className?lower_case}/${className}Component.java" />

	<instantiate from="src/app_package/Presenter.java.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${className?lower_case}/${className}Presenter.java" />
 
        <instantiate from="res/app_package/layout.xml.ftl"
                   to="${escapeXmlAttribute(resOut)}/layout/activity_${classToResource(className)}.xml" />

	<open file="${srcOut}/${className?lower_case}/${className}Presenter.java"/>
</recipe>
