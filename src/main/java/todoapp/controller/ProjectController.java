package todoapp.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import todoapp.model.Project;
import todoapp.util.ConnectionFactory;

public class ProjectController {
  public void create(Project project) throws SQLException {
    if (project == null) {
      throw new IllegalArgumentException("Project cannot be null");
    }
    String sql = "INSERT INTO projects (name, description, createdAt, updatedAt) VALUES (?, ?, ?, ?)";

    Connection conn = null;
    PreparedStatement stmt = null;

    try {
      conn = ConnectionFactory.getConnection();
      stmt = conn.prepareStatement(sql);
      stmt.setString(1, project.getName());
      stmt.setString(2, project.getDescription());
      stmt.setDate(3, new Date(project.getCreatedAt().getTime()));
      stmt.setDate(4, new Date(project.getUpdatedAt().getTime()));
      stmt.execute();
    } catch (SQLException ex) {
      throw new SQLException("Error saving project", ex);
    } finally {
      ConnectionFactory.closeConnection(conn, stmt);
    }
  }

  public void update(Project project) {
    String sql = "UPDATE projects SET name = ?, description = ?, createdAt = ?, updatedAt = ? WHERE id = ?";

    Connection conn = null;
    PreparedStatement stmt = null;

    try {
      conn = ConnectionFactory.getConnection();
      stmt = conn.prepareStatement(sql);
      stmt.setString(1, project.getName());
      stmt.setString(2, project.getDescription());
      stmt.setDate(3, new Date(project.getCreatedAt().getTime()));
      stmt.setDate(4, new Date(project.getUpdatedAt().getTime()));
      stmt.setInt(5, project.getId());
      stmt.execute();
    } catch (Exception ex) {
      throw new RuntimeException("Error updating project", ex);
    } finally {
      ConnectionFactory.closeConnection(conn, stmt);
    }
  }

  public void delete(int idProject) {
    String sql = "DELETE FROM projects WHERE id = ?";

    Connection conn = null;
    PreparedStatement stmt = null;

    try {
      conn = ConnectionFactory.getConnection();
      stmt = conn.prepareStatement(sql);
      stmt.setInt(1, idProject);
      stmt.execute();
    } catch (Exception ex) {
      throw new RuntimeException("Error deleting project", ex);
    } finally {
      ConnectionFactory.closeConnection(conn, stmt);
    }
  }

  public List<Project> findAll() {
    String sql = "SELECT * FROM projects";

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet resultSet = null;

    List<Project> projects = new ArrayList<Project>();

    try {
      conn = ConnectionFactory.getConnection();
      stmt = conn.prepareStatement(sql);

      resultSet = stmt.executeQuery();

      while (resultSet.next()) {
        Project project = new Project();
        project.setId(resultSet.getInt("id"));
        project.setName(resultSet.getString("name"));
        project.setDescription(resultSet.getString("description"));
        project.setCreatedAt(resultSet.getDate("createdAt"));
        project.setUpdatedAt(resultSet.getDate("updatedAt"));
        projects.add(project);
      }
    } catch (Exception ex) {
      throw new RuntimeException("Error finding projects", ex);
    } finally {
      ConnectionFactory.closeConnection(conn, stmt, resultSet);
    }

    return projects;
  }
}
