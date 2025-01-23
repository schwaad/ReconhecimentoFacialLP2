package com.reconhecimento_facial_lp2.springboot.model;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.opencv.imgproc.Imgproc;

public class FaceRecognition {

    public static void main(String[] args) {
        // Carregar a biblioteca nativa do OpenCV
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Inicializar a captura de vídeo da câmera (índice 0 para a primeira câmera)
        VideoCapture capture = new VideoCapture(0);
        if (!capture.isOpened()) {
            System.out.println("Erro ao acessar a câmera!");
            return;
        }

        // Matriz para armazenar os frames capturados
        Mat frame = new Mat();

        while (true) {
            capture.read(frame); // Captura um frame

            // Processar o frame (por exemplo, convertê-lo para escala de cinza)
            Mat grayFrame = new Mat();
            Imgproc.cvtColor(frame, grayFrame, Imgproc.COLOR_BGR2GRAY);

            // Aqui você pode adicionar lógica para detectar rostos ou outros objetos, etc.

            // Exibir a imagem com o rosto detectado (integrar com JavaFX, por exemplo)
        }
    }
}