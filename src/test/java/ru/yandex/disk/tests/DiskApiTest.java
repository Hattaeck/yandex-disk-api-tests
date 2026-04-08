package ru.yandex.disk.tests;


import static io.restassured.RestAssured.*;
import org.junit.jupiter.api.Test;

public class DiskApiTest {

    private final String myToken = "FOR YANDEX TEAM OPEN READ ME";

    @Test
    public void testGetDiskInfo() {

        given()
                .header("Authorization", "OAuth " + myToken )
                .when()
                .get("https://cloud-api.yandex.net/v1/disk/")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void testCreateFolder() {
        String folderName = "YandexTest_" + System.currentTimeMillis();

        given()
                .header("Authorization", "OAuth " + myToken)
                .queryParam("path", folderName)
                .when()
                .put("https://cloud-api.yandex.net/v1/disk/resources")
                .then()
                .statusCode(201);
    }

    @Test
    public void testCreateAndDeleteFolder() {
        String folderName = "FolderToDelete_" + System.currentTimeMillis();

        given()
                .header("Authorization", "OAuth " + myToken)
                .queryParam("path", folderName)
                .when()
                .put("https://cloud-api.yandex.net/v1/disk/resources")
                .then()
                .statusCode(201);

        given()
                .header("Authorization", "OAuth " + myToken)
                .queryParam("path", folderName)
                .when()
                .delete("https://cloud-api.yandex.net/v1/disk/resources")
                .then()
                .statusCode(204);
    }

    @Test
    public void testCopyFolder() {
        String folderSource = "SourceFolder_" + System.currentTimeMillis();
        String folderDestination = "DestFolder_" + System.currentTimeMillis();

        given()
                .header("Authorization", "OAuth " + myToken)
                .queryParam("path", folderSource)
                .when()
                .put("https://cloud-api.yandex.net/v1/disk/resources")
                .then()
                .statusCode(201);

        given()
                .header("Authorization", "OAuth " + myToken)
                .queryParam("from", folderSource)
                .queryParam("path", folderDestination)
                .when()
                .post("https://cloud-api.yandex.net/v1/disk/resources/copy")
                .then()
                .statusCode(201);
    }



}




