<?xml version="1.0"?>
<recipe>

<#if !createActivity>
    <mkdir at="${escapeXmlAttribute(srcOut)}" />
    
    <#if !(isLibraryProject??) || !isLibraryProject>
	    <mkdir at="${escapeXmlAttribute(srcOut)}/ui" />
	    <mkdir at="${escapeXmlAttribute(srcOut)}/data" />
	    <mkdir at="${escapeXmlAttribute(srcOut)}/data/remote" />
	    <mkdir at="${escapeXmlAttribute(srcOut)}/data/remote/model" />
	    <mkdir at="${escapeXmlAttribute(srcOut)}/data/local" />
    </#if>
</#if>

    <mkdir at="${escapeXmlAttribute(projectOut)}/libs" />

    <merge from="root/settings.gradle.ftl"
             to="${escapeXmlAttribute(topOut)}/settings.gradle" />
    <instantiate from="root/build.gradle.ftl"
                   to="${escapeXmlAttribute(projectOut)}/build.gradle" />
    <instantiate from="root/AndroidManifest.xml.ftl"
                   to="${escapeXmlAttribute(manifestOut)}/AndroidManifest.xml" />

    <#if !(isLibraryProject??) || !isLibraryProject>

  	    <instantiate from="root/src/app_package/App.java.ftl"
		           to="${escapeXmlAttribute(srcOut)}/${extractLetters(appTitle)}App.java" />

	    <instantiate from="root/src/app_package/AppComponent.java.ftl"
		           to="${escapeXmlAttribute(srcOut)}/${extractLetters(appTitle)}AppComponent.java" />

	    <instantiate from="root/src/app_package/AppModule.java.ftl"
		           to="${escapeXmlAttribute(srcOut)}/${extractLetters(appTitle)}AppModule.java" />

	    <instantiate from="root/src/app_package/ui/UIModule.java.ftl"
		           to="${escapeXmlAttribute(srcOut)}/ui/UIModule.java" />

	    <instantiate from="root/src/app_package/data/DataSource.java.ftl"
		           to="${escapeXmlAttribute(srcOut)}/data/DataSource.java" />

	    <instantiate from="root/src/app_package/data/DataSourceModule.java.ftl"
		           to="${escapeXmlAttribute(srcOut)}/data/DataSourceModule.java" />

	    <instantiate from="root/src/app_package/data/remote/ApiService.java.ftl"
		           to="${escapeXmlAttribute(srcOut)}/data/remote/ApiService.java" />

	    <instantiate from="root/src/app_package/data/local/DbHelper.java.ftl"
		           to="${escapeXmlAttribute(srcOut)}/data/local/DbHelper.java" />

	    <instantiate from="root/src/app_package/data/local/Entity.java.ftl"
		           to="${escapeXmlAttribute(srcOut)}/data/local/Entity.java" />

	    <instantiate from="root/src/app_package/data/local/EntityDao.java.ftl"
		           to="${escapeXmlAttribute(srcOut)}/data/local/EntityDao.java" />
    </#if>

<mkdir at="${escapeXmlAttribute(resOut)}/drawable" />
<#if copyIcons && !isLibraryProject>
    <copy from="root/res/mipmap-hdpi"
            to="${escapeXmlAttribute(resOut)}/mipmap-hdpi" />
    <copy from="root/res/mipmap-mdpi"
            to="${escapeXmlAttribute(resOut)}/mipmap-mdpi" />
    <copy from="root/res/mipmap-xhdpi"
            to="${escapeXmlAttribute(resOut)}/mipmap-xhdpi" />
    <copy from="root/res/mipmap-xxhdpi"
            to="${escapeXmlAttribute(resOut)}/mipmap-xxhdpi" />
    <copy from="root/res/mipmap-xxxhdpi"
            to="${escapeXmlAttribute(resOut)}/mipmap-xxxhdpi" />
</#if>
<#if makeIgnore>
    <copy from="root/module_ignore"
            to="${escapeXmlAttribute(projectOut)}/.gitignore" />
</#if>
<#if enableProGuard>
    <instantiate from="root/proguard-rules.txt.ftl"
                   to="${escapeXmlAttribute(projectOut)}/proguard-rules.pro" />
</#if>
<#if !(isLibraryProject??) || !isLibraryProject>
    <instantiate from="root/res/values/styles.xml.ftl"
                   to="${escapeXmlAttribute(resOut)}/values/styles.xml" />
<#if buildApi gte 22>
    <copy from="root/res/values/colors.xml"
          to="${escapeXmlAttribute(resOut)}/values/colors.xml" />
</#if>
</#if>

    <instantiate from="root/res/values/strings.xml.ftl"
                   to="${escapeXmlAttribute(resOut)}/values/strings.xml" />

    <instantiate from="root/test/app_package/ExampleInstrumentedTest.java.ftl"
                   to="${escapeXmlAttribute(testOut)}/ExampleInstrumentedTest.java" />

<#if unitTestsSupported>
    <instantiate from="root/test/app_package/ExampleUnitTest.java.ftl"
                   to="${escapeXmlAttribute(unitTestOut)}/ExampleUnitTest.java" />
</#if>
<#if includeCppSupport!false>
    <instantiate from="root/CMakeLists.txt.ftl"
                   to="${escapeXmlAttribute(projectOut)}/CMakeLists.txt" />

    <mkdir at="${nativeSrcOut}" />
    <instantiate from="root/native-lib.cpp.ftl" to="${nativeSrcOut}/native-lib.cpp" />
</#if>

</recipe>
