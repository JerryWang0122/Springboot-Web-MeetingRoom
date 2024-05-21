<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<%-- Tomcat 10.x JSTL --%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!-- Spring Form 表單標籤 -->
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags/form" %>
<!--
    +-----------+-------------+
    | 預約表單   |
    | xxxxx     |
    +-----------+    回
    | 取消預約   |    應
    | xxxxx    |     結
    +----------+     果
    | 查詢預約   |
    +----------+
    | 新增會議室 |
    +----------+-------------+
-->
<html>
    <head>
        <meta charset="UTF-8">
        <title>Booking Meeting Room</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css" />
    </head>
    <body style="padding: 15px">
        <table>
            <tr>
                <!-- 預約表單 -->
                <td valign="top">
                    <sp:form modelAttribute="bookingMeetingRoom" method="post" action="/booking" class="pure-form" target="resultFrame">
                        <fieldset>
                            <legend>預約表單</legend>
                            會議室：
                                <sp:select path="roomId" items="${ rooms }" itemValue="roomId" itemLabel="roomName" /><p>
                            預約人：
                                <sp:select path="userId" items="${ users }" itemValue="id" itemLabel="name"/> <p>
                            預約日：
                                <sp:input path="bookingDate" type="date" required="required"/><p>
                            <button type="submit" class="pure-button pure-button-primary">傳送</button>
                        </fieldset>
                    </sp:form>

                    <!-- 取消預約 -->
                    <form action="/booking" method="post" class="pure-form" target="resultFrame">
                        <fieldset>
                            <legend>取消預約</legend>
                            預約Id：<input type="number" name="bookingId" id="bookingId" required></p>
                            <input type="hidden" id="_method" name="_method" value="DELETE">
                            <button type="submit" class="pure-button pure-button-primary">送出</button>
                        </fieldset>
                    </form>

                    <!-- 查詢預約 -->
                    <form action="/booking/findAll" method="get" class="pure-form" target="resultFrame">
                        <fieldset>
                            <legend>查詢預約</legend>

                            <button type="submit" class="pure-button pure-button-primary">查詢</button>
                        </fieldset>
                    </form>

                    <!-- 新增會議室 -->
                </td>
                <!-- 回應結果 -->
                <td valign="top">
                    <iframe name="resultFrame" style="border: 0px" width="1200px" height="1000px"></iframe>
                </td>
            </tr>
        </table>
    </body>
    <footer>
        <%=application.getServerInfo()%>
    </footer>
</html>