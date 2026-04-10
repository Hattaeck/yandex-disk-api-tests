package ru.yandex.disk.tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.yandex.disk.Endpoints;

import static io.restassured.RestAssured.given;

public class DiskApiTest {

    private static RequestSpecification requestSpec;
    private static final String MY_TOKEN = System.getenv("YANDEX_TOKEN");
    @BeforeAll
    public static void setup() {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://cloud-api.yandex.net/")
                .addHeader("Authorization", "OAuth " + MY_TOKEN)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }


    @Test
    @DisplayName("GET: Инфо о диске")
    public void testGetDiskInfo() {
        given().spec(requestSpec).when().get(Endpoints.DISK).then().statusCode(200);
    }

    @Test
    @DisplayName("PUT: Создать папку")
    public void testCreateFolder() {
        String path = "Folder_" + System.currentTimeMillis();
        given().spec(requestSpec).queryParam("path", path).when().put(Endpoints.RESOURCES).then().statusCode(201);
    }

    @Test
    @DisplayName("POST: Копировать")
    public void testCopyFolder() {
        String src = "Src_" + System.currentTimeMillis();
        String dst = "Dst_" + System.currentTimeMillis();
        given().spec(requestSpec).queryParam("path", src).put(Endpoints.RESOURCES);
        given().spec(requestSpec).queryParam("from", src).queryParam("path", dst).when().post(Endpoints.COPY).then().statusCode(201);
    }

    @Test
    @DisplayName("POST: Переместить (MOVE)")
    public void testMoveFolder() {
        String src = "MoveSrc_" + System.currentTimeMillis();
        String dst = "MoveDst_" + System.currentTimeMillis();
        given().spec(requestSpec).queryParam("path", src).put(Endpoints.RESOURCES);
        given().spec(requestSpec).queryParam("from", src).queryParam("path", dst).when().post(Endpoints.MOVE).then().statusCode(201);
    }

    @Test
    @DisplayName("DELETE: Удалить папку")
    public void testDeleteResource() {
        String path = "Del_" + System.currentTimeMillis();
        given().spec(requestSpec).queryParam("path", path).put(Endpoints.RESOURCES);
        given().spec(requestSpec).queryParam("path", path).when().delete(Endpoints.RESOURCES).then().statusCode(204);
    }

    @Test
    @DisplayName("PUT: Опубликовать папку")
    public void testPublishResource() {
        String path = "Pub_" + System.currentTimeMillis();
        given().spec(requestSpec).queryParam("path", path).put(Endpoints.RESOURCES);
        given().spec(requestSpec).queryParam("path", path).when().put(Endpoints.PUBLISH).then().statusCode(200);
    }

    @Test
    @DisplayName("DELETE: Отменить публикацию")
    public void testUnpublishResource() {
        String path = "Unpub_" + System.currentTimeMillis();

        given().spec(requestSpec).queryParam("path", path).put(Endpoints.RESOURCES);
        given().spec(requestSpec).queryParam("path", path).put(Endpoints.PUBLISH);

        given()
                .spec(requestSpec)
                .queryParam("path", path)
                .when()
                .delete(Endpoints.UNPUBLISH)
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("DELETE: Очистить корзину")
    public void testClearTrash() {
        given().spec(requestSpec).when().delete(Endpoints.TRASH).then().statusCode(204);
    }

    @Test
    @DisplayName("401: Плохой токен")
    public void testInvalidToken() {
        given().baseUri("https://cloud-api.yandex.net/").header("Authorization", "OAuth XXX").when().get(Endpoints.DISK).then().statusCode(401);
    }
}