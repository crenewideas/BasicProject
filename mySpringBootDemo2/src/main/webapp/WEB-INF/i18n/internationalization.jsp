<%@ page language="java" import="java.util.*" pageEncoding="uTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE HTML>
<html>
  <head>
     <title>国际化（i18n）测试</title>
  </head>

  <body>
    <a href="./page?language=zn_CH">简体中文</a>
    <a href="./page?language=en_US">英文</a>
    <h2>
        <spring:message code="msg"/>
    </h2>
    <h2>
        Locale : ${pageContext.response.locale}
    </h2>
  </body>

</html>