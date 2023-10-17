# Spring Framework 
1. 경량 컨테이너
2.  IoC(Invertion of Control:제어역행) : new 연사자를 통해 인스턴스를 생성하고 메서드를 호출하는 생명주기에 대한 작업을 스프링에 위임
3. DI(Dependency Injection: 의존성 주입) : DI를 이용하여 빈(Bean)객체 관리, 스프링컨테이너에 클래스를 등록하면 인스턴스 관리
4. AOP(Aspect_Oriented Programming:관점지향 프로그래밍)

- Bean 설정 -> XML을 통한 DI , Annotations를 이용한 DI


### XML을 통한 DI bean 태그를 사용하기 위한 선언 
	
    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    	xmlns:jdbc="http://www.springframework.org/schema/jdbc"
    	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.0.xsd
    	http://www.springframework.org/schema/jdbc  http://www.springframework.org/schema/jdbc/spring-jdbc-4.0.xsd">
	
	<bean id="사용자 구현체 클래스" class="com.lotts.domain.logic.UserImpl">
		<property name="클래스 인터페이스" ref="클래스 인터페이스"/>
	</bean>


### Annotations를 이용한 DI

@Repository -  데이터베이스에서 정보를 검색하는 DAO(Data Access Objects)에 사용됩니다.

@Service - 서비스 계층 클래스에 사용되며 데이터 및 비즈니스 로직 처리에 사용됩니다.

@Controller - UI에서 요청 처리에 사용됩니다.

@RequestMapping(value=”“) - 요청 들어온 URI의 요청과 Annotation value 값이 일치하면 해당 클래스나 메소드가 실행( Controller 객체 안의 메서드와 클래스에 적용 가능)

@RequestBody - Body에 전달되는 데이터를 메소드의 인자와 매칭시켜, 데이터를 받아서 처리(라이언트가 보내는 HTTP 요청 본문(JSON 및 XML 등)을 Java 오브젝트로 변환)

@ResponseBody - 메소드에서 리턴되는 값이 View 로 출력되지 않고 HTTP Response Body에 직접 쓰여지게 됨

@ModelAttribute - 클라이언트가 전송하는 HTTP parameter, Body 내용을 Setter 함수를 통해 1:1로 객체에 데이터를 연결

- 스프링은 개발 관련 설정 들을 직접 작성해주어야함

# Spring Boot 

- 스프링 프레임워크를 사용하기 위한 설정들을 자동화-> 편하게 스프링 활용

스프링 부트 Starter 디펜던시만 추가(dependency 자동화)하면 API정의, 웹 애플리케이션 서버를 실행(톰캣 내장),

환경이나 의존성관리등 신경 쓸 필요없이 바로 코딩 시작가능
