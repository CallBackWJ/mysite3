<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
	href="${pageContext.servletContext.contextPath }/assets/css/user.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
	var FormValidator = {
		$imageCheck : null,
		$buttonCheckEmail : null,
		$inputTextEmail : null,

		init : function() {
			this.$imageCheck = $("#img-checkemail");
			this.$buttonCheckEmail = $("#btn-checkemail");
			this.$inputTextEmail = $("#email");

			this.$inputTextEmail.change(this.onEmailInputTextChanged.bind(this));
			this.$buttonCheckEmail.click(this.onCheckEmailButtonClicked.bind(this));
			$("#join-form").submit(this.onFormSubmit.bind(this));
		},
		onEmailInputTextChanged : function() {
			this.$imageCheck.hide();
			this.$buttonCheckEmail.show();
		},
		onCheckEmailButtonClicked : function(event) {
			console.log(event.currentTarget);

			var email = this.$inputTextEmail.val();
			if (email === "") {
				return;
			}

			//ajax 통신
			$.ajax({
						url : "${pageContext.request.contextPath }/user/api/checkemail?email="+ email,
						type : "get",
						dataType : "json",
						data : "",
						success : this.onCheckEmailAjaxSuccess.bind(this),
						error : this.onCheckEmailAjaxError.bind(this)
					});
		},
		onCheckEmailAjaxSuccess : function(response) {
			if (response.result == "fail") {
				console.error(response.message);
				return;
			}

			if (response.data == true) {
				alert("이미 존재하는 이메일 입니다. 다른 이메일을 사용해 주세요.");
				// email 입력 창 비우고 포커싱
				this.$inputTextEmail.val("").focus();
			} else {
				this.$imageCheck.show();
				this.$buttonCheckEmail.hide();
			}
		},
		onCheckEmailAjaxError : function(jqXHR, status, error) {
			console.error(status + " : " + error);
		},
		onFormSubmit : function() {
			//1. 이름
			var $inputTextName = $("#name");
			if ($inputTextName.val() === "") {
				alert("이름은 필수 항목입니다.");
				$inputTextName.focus();
				return false;
			}
			var $email = $("#email");
			if (this.$inputTextEmail.val() === "") {
				alert("이메일은 필수 항목입니다.");
				this.$inputTextEmail.focus();
				return false;
			}

			//3. 이메일 중복 체크 여부
			if (this.$imageCheck.is(":visible") === false) {
				alert("이메일 중복 체크를 해 주세요.");
				return false;
			}

			//4. 비밀번호
			var $inputPassword = $("#password");
			if ($inputPassword.val() === "") {
				alert("비밀번호는 필수 항목입니다.");
				$inputPassword.focus();
				return false;
			}

			//5. 약관동의
			var $inputCheckBoxAgree = $("#agree-prov");
			if ($inputCheckBoxAgree.is(":checked") === false) {
				alert("가입 약관에 동의 하셔야 합니다.");
				$inputCheckBoxAgree.focus();
				return false;
			}
			// valid!
			return true;
		}
	}
	$(function() {
		FormValidator.init();
	});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="user">

				<form:form modelAttribute="userVo" id="join-form" name="joinForm"
					method="post"
					action="${pageContext.servletContext.contextPath }/user/join">
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="${userVo.name}">
					<spring:hasBindErrors name="userVo">
						<c:if test="${errors.hasFieldErrors('name') }">
							<p style="margin: 0; padding: 0; color: red; text-align: left">
								<strong><spring:message
										code="${errors.getFieldError( 'name' ).codes[0] }"
										text="${errors.getFieldError( 'name' ).defaultMessage }" /> </strong>
							</p>
						</c:if>
					</spring:hasBindErrors>



					<label class="block-label" for="email">이메일</label>
					<form:input path="email" />

					<img id="img-checkemail" style="width: 25px; display: none"
						src="${pageContext.servletContext.contextPath }/assets/images/check.png" />


					<input id="btn-checkemail" type="button" value="이메일확인">
					<label class="block-label">패스워드</label>
					
					<form:password path="password" />
					<p style="margin: 0; padding: 0; color: red; text-align: left;">
						<strong> <form:errors path="email" />
						</strong>
					</p>


					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="female"
							checked="checked"> <label>남</label> <input type="radio"
							name="gender" value="male">
					</fieldset>

					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>

					<input type="submit" value="가입하기">

				</form:form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>