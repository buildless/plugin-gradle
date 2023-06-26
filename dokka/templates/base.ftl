<#import "includes/page_metadata.ftl" as page_metadata>
<#import "includes/header.ftl" as header>
<#import "includes/footer.ftl" as footer>
<!DOCTYPE html>
<html class="no-js" prefix="og: https://ogp.me/ns#">
<head>
    <meta charset="utf-8" />
    <meta property="og:title" content="Buildless for Gradle" />
    <meta property="og:type" content="website" />
    <meta property="og:url" content="https://docs.gradle.less.build/" />
    <meta property="og:image" content="https://docs.gradle.less.build/images/ogimage-banner-r1@1x.jpg" />
    <meta name="viewport" content="width=device-width, initial-scale=1" charset="UTF-8">
    <@page_metadata.display/>
    <@template_cmd name="pathToRoot"><script>var pathToRoot = "${pathToRoot}";</script></@template_cmd>
    <script>document.documentElement.classList.replace("no-js","js");</script>
    <#-- This script doesn't need to be there but it is nice to have
    since app in dark mode doesn't 'blink' (class is added before it is rendered) -->
    <script>const storage = localStorage.getItem("dokka-dark-mode")
      if (storage == null) {
        const osDarkSchemePreferred = window.matchMedia && window.matchMedia('(prefers-color-scheme: dark)').matches
        if (osDarkSchemePreferred === true) {
          document.getElementsByTagName("html")[0].classList.add("theme-dark")
        }
      } else {
        const savedDarkMode = JSON.parse(storage)
        if(savedDarkMode === true) {
          document.getElementsByTagName("html")[0].classList.add("theme-dark")
        }
      }
    </script>
    <script async src="https://www.googletagmanager.com/gtag/js?id=G-TLDWYQSZ6B"></script>
    <script>
      window.dataLayer = window.dataLayer || [];
      function gtag(){dataLayer.push(arguments);}
      gtag('js', new Date());

      gtag('config', 'G-TLDWYQSZ6B');
    </script>
    <script>
      var APP_ID = "zh0l96si";
      window.intercomSettings = {
        app_id: APP_ID
      };
      (function(){var w=window;var ic=w.Intercom;if(typeof ic==="function"){ic('reattach_activator');ic('update',w.intercomSettings);}else{var d=document;var i=function(){i.c(arguments);};i.q=[];i.c=function(args){i.q.push(args);};w.Intercom=i;var l=function(){var s=d.createElement('script');s.type='text/javascript';s.async=true;s.src='https://widget.intercom.io/widget/' + APP_ID;var x=d.getElementsByTagName('script')[0];x.parentNode.insertBefore(s, x);};if(document.readyState==='complete'){l();}else if(w.attachEvent){w.attachEvent('onload',l);}else{w.addEventListener('load',l,false);}}})();
    </script>
    <#-- Resources (scripts, stylesheets) are handled by Dokka.
    Use customStyleSheets and customAssets to change them. -->
    <@resources/>
</head>
<body>
<div class="root">
    <@header.display/>
    <div id="container">
        <div class="sidebar" id="leftColumn">
            <div class="sidebar--inner" id="sideMenu"></div>
        </div>
        <div id="main">
            <@content/>
            <@footer.display/>
        </div>
    </div>
</div>
<script type="application/ld+json">
    {
        "@context": "https://schema.org",
        "@type": "SoftwareSourceCode",
        "name": "Buildless for Gradle",
        "alternateName": "Buildless Plugin for Gradle",
        "runtimePlatform": "Java 11",
        "programmingLanguage": "Kotlin",
        "creativeWorkStatus": "Published",
        "codeRepository": "https://github.com/buildless/plugin-gradle",
        "targetProduct": {
            "name": "Gradle",
            "alternateName": "Gradle Plugins",
            "publisher": {
                "@type": "Organization",
                "name": "Gradle, Inc."
            }
        },
        "author": {
            "@type": "Person",
            "givenName": "Sam",
            "familyName": "Gammon",
            "url": "https://github.com/sgammon"
        },
        "publisher": {
            "@type": "Organization",
            "name": "Buildless",
            "url": "https://less.build",
            "sameAs": [
                "https://github.com/buildless"
            ]
        }
    }
</script>
</body>
