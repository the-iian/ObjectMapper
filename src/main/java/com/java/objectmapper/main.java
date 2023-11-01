package com.java.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.java.objectmapper.dto.Car;
import com.java.objectmapper.dto.User;
import java.util.Arrays;
import java.util.List;

public class main {
    public static void main(String[] args) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper(); // 객체 생성

        User user = new User();
        user.setName("홍길동");
        user.setAge(30);

        Car car1 = new Car();
        car1.setName("K5");
        car1.setCarNumber("11가 1111");
        car1.setType("sedan");

        Car car2 = new Car();
        car2.setName("Q5");
        car2.setCarNumber("22가 2222");
        car2.setType("SUV");

        List<Car> carList = Arrays.asList(car1, car2); // 리스트 생성
        user.setCars(carList);

        // System.out.println(user);

        String json = objectMapper.writeValueAsString(user);
        System.out.println(json);

        // 변수타입이나 모든걸 알수있을 때 만들 수 있는 방식
        // 특정 라이브러리를 만들어서 키를 돌리거나, 반복해야하거나, 직접 노드를 건드릴때 쓰는 방식
        JsonNode jsonNode = objectMapper.readTree(json); // 객체 생성 후 문자열 json 담기
        String _name = jsonNode.get("name").asText();
        int _age = jsonNode.get("age").asInt(); // 형변환
        System.out.println("name : " +_name);
        System.out.println("age : " +_age);

        String _list = jsonNode.get("cars").asText();
        System.out.println(_list);

        // 배열의 노드를 표현
        JsonNode cars = jsonNode.get("cars");
        ArrayNode arrayNode = (ArrayNode)cars; // object mapper의 클래스, array node로 형변환


        // json 파싱
        List<Car> _cars = objectMapper.convertValue(arrayNode, new TypeReference<List<Car>>() {});
        // object를 받아 type List의 car가 들어간 list의 형태로 변환시킴
        // convertValue 데이터 변환 또는 매핑작업 수행 메서드, json이 아닌 원하는 클래스로 맵핑시킬 수 있다

        System.out.println(_cars);


        // node 외부 값 변경
        ObjectNode objectNode = (ObjectNode) jsonNode;
        objectNode.put("name", "steve");
        objectNode.put("age", 20);

        System.out.println(objectNode.toPrettyString()); // json을 예쁘게 출력

    }
}
