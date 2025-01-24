package com.reconhecimento_facial_lp2.springboot.service;

import com.reconhecimento_facial_lp2.springboot.model.User;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Service
public class UserService {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/reconhecimento-facial-lp2";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "teste123";

    /**
     * Cadastra um novo usuário no banco de dados.
     *
     * @param name     Nome do usuário.
     * @param faceHash Hash facial do usuário.
     * @return Mensagem indicando sucesso ou falha.
     */
    public String registerUser(String name, String faceHash) {
        String checkQuery = "SELECT 1 FROM user WHERE face_hash = ?";
        String insertQuery = "INSERT INTO user (name, face_hash) VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Verifica se o hash facial já existe
            try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {
                checkStatement.setString(1, faceHash);
                try (ResultSet resultSet = checkStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return "Erro: Usuário com este hash facial já cadastrado.";
                    }
                }
            }

            // Insere o novo usuário
            try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                insertStatement.setString(1, name);
                insertStatement.setString(2, faceHash);
                insertStatement.executeUpdate();
                return "Usuário cadastrado com sucesso.";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao cadastrar usuário: " + e.getMessage();
        }
    }

    /**
     * Verifica se um usuário existe com base no hash facial.
     *
     * @param faceHash Hash facial do usuário.
     * @return true se o usuário existir, false caso contrário.
     */
    public boolean isUserExists(String faceHash) {
        String query = "SELECT 1 FROM user WHERE face_hash = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, faceHash);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Remove um usuário pelo hash facial.
     *
     * @param faceHash Hash facial do usuário.
     * @return Mensagem indicando sucesso ou falha.
     */
    public String deleteUser(String faceHash) {
        String query = "DELETE FROM user WHERE face_hash = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, faceHash);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                return "Usuário removido com sucesso.";
            } else {
                return "Erro: Usuário não encontrado.";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao remover usuário: " + e.getMessage();
        }
    }
}
