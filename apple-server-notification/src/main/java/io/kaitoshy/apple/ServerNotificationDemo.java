/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.kaitoshy.apple;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ServerNotificationDemo {
    private static final String teamId = "<TEAM_ID>";

    // bound id
    private static final String topic = "io.kaitoshy";

    private static final String url = "https://api.push.apple.com:443/3/device/";

    private static final String pushToken = "";

    private static final String authId = "auth_id";

    // 生成
    private static final String authKeyPath = "<P8 file>";

    private static final OkHttpClient client = new OkHttpClient()
            .newBuilder()
            .connectTimeout(10000, TimeUnit.MILLISECONDS)
            .build();

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        String authToken = createToken();
        System.out.println(authToken);
        String a = post(url + pushToken, "{\"aps\":{\"alert\":\"You have a new message1\"},\"payload\":\"Hello from LiveCode\"}", authToken);
        System.out.println(a);
    }


    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public static String post(String url, String json, String authToken) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .addHeader("authorization", "bearer " + authToken)
                .addHeader("apns-expiration", "0")
                .addHeader("apns-topic", topic)
                .addHeader("apns-push-type", "alert")
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static String createToken() throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {

        ECPrivateKey ecPrivateKey = genECPrivateKeyFromP8(authKeyPath);

        return JWT.create().withKeyId(authId)
                .withIssuer(teamId)
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .sign(Algorithm.ECDSA256(null,ecPrivateKey));
    }

    public static ECPrivateKey genECPrivateKeyFromP8(String filePath) throws
            NoSuchAlgorithmException,
            InvalidKeySpecException,
            IOException {
        File file = new File(authKeyPath);
        String st;

        StringBuilder strBuilder = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(file));

        while ((st = br.readLine()) != null){
            strBuilder.append(st);
        }

        String privateKeyPEM = strBuilder.toString();
        privateKeyPEM = privateKeyPEM
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s", "");

        byte[] keyBytes = java.util.Base64.getDecoder().decode(privateKeyPEM);

        KeyFactory keyFactory = KeyFactory.getInstance("EC");

        PrivateKey privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(keyBytes));
        return (ECPrivateKey) privateKey;
    }

}
