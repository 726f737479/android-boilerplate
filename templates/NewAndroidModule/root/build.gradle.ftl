<#if !(perModuleRepositories??) || perModuleRepositories>
buildscript {
    repositories {
        jcenter()
<#if mavenUrl != "mavenCentral">
        maven {
            url '${mavenUrl}'
        }
</#if>
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:${gradlePluginVersion}'
    }
}
</#if>
<#if isLibraryProject?? && isLibraryProject>
apply plugin: 'com.android.library'
<#else>
apply plugin: 'com.android.application'
</#if>

apply plugin: 'me.tatarka.retrolambda'
apply plugin: 'com.neenbedankt.android-apt'

<#if !(isLibraryProject??) || !isLibraryProject>
def type = 'int'
def levelType = 'LOG_LEVEL'
def levelDebug = 'android.util.Log.VERBOSE'
def levelRelease = 'android.util.Log.WARN'
</#if>

<#if !(perModuleRepositories??) || perModuleRepositories>

repositories {
        jcenter()
<#if mavenUrl != "mavenCentral">
        maven {
            url '${mavenUrl}'
        }
</#if>
}
</#if>

android {
    compileSdkVersion <#if buildApiString?matches("^\\d+$")>${buildApiString}<#else>'${buildApiString}'</#if>
    buildToolsVersion "${buildToolsVersion}"

    defaultConfig {
    <#if isLibraryProject?? && isLibraryProject>
    <#else>
    applicationId "${packageName}"
    </#if>
        minSdkVersion <#if minApi?matches("^\\d+$")>${minApi}<#else>'${minApi}'</#if>
        targetSdkVersion <#if targetApiString?matches("^\\d+$")>${targetApiString}<#else>'${targetApiString}'</#if>
        versionCode 1
        versionName "1.0"

        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"

    <#if includeCppSupport!false && cppFlags != "">
        externalNativeBuild {
            cmake {
                cppFlags "${cppFlags}"
            }
        }
    </#if>
    }
<#if javaVersion?? && (javaVersion != "1.6" && buildApi lt 21 || javaVersion != "1.7")>

    compileOptions {
        sourceCompatibility JavaVersion.VERSION_${javaVersion?replace('.','_','i')}
        targetCompatibility JavaVersion.VERSION_${javaVersion?replace('.','_','i')}
    }
</#if>
    
<#if !(isLibraryProject??) || !isLibraryProject>
    dataBinding {
        enabled = true
    }
</#if>

<#if enableProGuard>
    buildTypes {
	debug {
            minifyEnabled false
<#if !(isLibraryProject??) || !isLibraryProject>
            buildConfigField type, levelType, levelDebug
</#if>
        }

        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
<#if !(isLibraryProject??) || !isLibraryProject>
            buildConfigField type, levelType, levelRelease
</#if>
        }
    }
</#if>
<#if includeCppSupport!false>
    externalNativeBuild {
        cmake {
            path "CMakeLists.txt"
        }
    }
</#if>
}

dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
	
<#if !(isLibraryProject??) || !isLibraryProject>
    compile brainbeanapps.core

    compile support.design
    compile support.appcompat
    compile support.recyclerview

    compile rx.java
    compile rx.android
    compile rx.androidProguard
	
    compile dagger.dagger
    apt dagger.compiler
    provided dagger.annotations

    compile square.timber
    compile square.retrofit.core
    compile square.retrofit.gson
    compile square.retrofit.rxjava
    compile square.okhttp.loggingInterceptor

    compile square.picasso

    compile ormlite.core
    compile ormlite.android

    androidTestCompile support.annotations
    androidTestCompile integrationTesting.runner
    androidTestCompile integrationTesting.support
    androidTestCompile integrationTesting.rules

    testCompile testing.junit
    testCompile testing.mockito
    testCompile testing.robolectric.core
</#if>
<#if WearprojectName?has_content && NumberOfEnabledFormFactors?has_content && NumberOfEnabledFormFactors gt 1 && Wearincluded>
    wearApp project(':${WearprojectName}')
    compile 'com.google.android.gms:play-services:+'
</#if>
}
