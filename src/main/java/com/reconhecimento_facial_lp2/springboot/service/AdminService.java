package com.reconhecimento_facial_lp2.springboot.service;

import com.reconhecimento_facial_lp2.springboot.model.Admin;
import com.reconhecimento_facial_lp2.springboot.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public static boolean isPasswordValid(String senha) {
        String query = "SELECT 1 FROM admin WHERE senha = ?";
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/reconhecimento-facial-lp2", "postgres", "teste123");
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, senha);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // Retorna true se a senha existir
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

