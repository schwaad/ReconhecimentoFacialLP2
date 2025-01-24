package com.reconhecimento_facial_lp2.springboot.model;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.highgui.HighGui;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class FaceRecognition {

    public static void main(String[] args) {
        // Carregar a biblioteca nativa do OpenCV
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Carregar o classificador em cascata para detecção facial
        CascadeClassifier faceDetector = new CascadeClassifier("src/main/resources/haarcascade_frontalface_alt.xml");
        if (faceDetector.empty()) {
            System.out.println("Erro ao carregar o classificador Haar!");
            return;
        }

        // Inicializar a captura de vídeo da câmera (índice 0 para a primeira câmera)
        VideoCapture capture = new VideoCapture(0);
        if (!capture.isOpened()) {
            System.out.println("Erro ao acessar a câmera!");
            return;
        }

        Mat frame = new Mat();
        System.out.println("Pressione 'ESC' para sair.");

        while (true) {
            // Captura o frame
            if (!capture.read(frame)) {
                System.out.println("Erro ao capturar o frame!");
                break;
            }

            // Converte o frame para escala de cinza
            Mat grayFrame = new Mat();
            Imgproc.cvtColor(frame, grayFrame, Imgproc.COLOR_BGR2GRAY);

            // Detectar rostos no frame
            MatOfRect faces = new MatOfRect();
            faceDetector.detectMultiScale(grayFrame, faces);

            // Iterar sobre os rostos detectados
            for (Rect rect : faces.toArray()) {
                // Desenhar um retângulo ao redor do rosto
                Imgproc.rectangle(frame, rect, new Scalar(0, 255, 0), 2);

                // Extrair a região do rosto
                Mat faceRegion = new Mat(grayFrame, rect);

                // Gerar e exibir o hash do rosto
                String faceHash = generateHash(faceRegion);
                if (faceHash != null) {
                    System.out.println("Código Hash do Rosto: " + faceHash);
                }
            }

            // Exibir o frame processado
            HighGui.imshow("Reconhecimento Facial", frame);

            // Adiciona delay para exibição (30 ms) e verifica se 'ESC' foi pressionado
            int key = HighGui.waitKey(30);
            if (key == 27) { // Código ASCII para 'ESC'
                System.out.println("Saindo...");
                break;
            }
        }

        // Libera os recursos
        capture.release();
        HighGui.destroyAllWindows();
    }

    /**
     * Gera um hash SHA-256 único para a região do rosto fornecida.
     *
     * @param faceRegion Matriz da região do rosto.
     * @return Hash do rosto como uma string Base64.
     */
    private static String generateHash(Mat faceRegion) {
        try {
            // Redimensionar a região do rosto para um tamanho fixo
            Mat resizedFace = new Mat();
            Size fixedSize = new Size(100, 100);
            Imgproc.resize(faceRegion, resizedFace, fixedSize);

            // Normalizar os valores dos pixels
            Core.normalize(resizedFace, resizedFace, 0, 255, Core.NORM_MINMAX);

            // Extrair os dados da matriz do rosto
            byte[] faceBytes = new byte[(int) (resizedFace.total() * resizedFace.elemSize())];
            resizedFace.get(0, 0, faceBytes);

            // Gerar um hash SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(faceBytes);

            // Retornar o hash como string em Base64
            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Erro ao criar hash: " + e.getMessage());
            return null;
        }
    }
}
