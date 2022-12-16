package todoapp.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import todoapp.model.Task;
import todoapp.util.ConnectionFactory;

public class TaskController {
  public void create(Task task) throws SQLException {
    String sql = "INSERT INTO tasks (idProject, name, description, status, notes, deadline, createdAt, updatedAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    Connection conn = null;
    PreparedStatement stmt = null;

    try {
      conn = ConnectionFactory.getConnection();
      stmt = conn.prepareStatement(sql);
      stmt.setInt(1, task.getIdProject());
      stmt.setString(2, task.getName());
      stmt.setString(3, task.getDescription());
      stmt.setBoolean(4, task.isCompleted());
      stmt.setString(5, task.getNotes());
      stmt.setDate(6, new Date(task.getDeadline().getTime()));
      stmt.setDate(7, new Date(task.getCreatedAt().getTime()));
      stmt.setDate(8, new Date(task.getUpdatedAt().getTime()));
      stmt.execute();
    } catch (Exception ex) {
      throw new RuntimeException("Error saving task", ex);
    } finally {
      ConnectionFactory.closeConnection(conn, stmt);
    }
  }

  public void update(Task task) {
    String sql = "UPDATE tasks SET idProject = ?, name = ?, description = ?, status = ?, notes = ?, deadline = ?, createdAt = ?, updatedAt = ? WHERE id = ?";

    Connection conn = null;
    PreparedStatement stmt = null;

    try {
      conn = ConnectionFactory.getConnection();
      stmt = conn.prepareStatement(sql);
      stmt.setInt(1, task.getIdProject());
      stmt.setString(2, task.getName());
      stmt.setString(3, task.getDescription());
      stmt.setBoolean(4, task.isCompleted());
      stmt.setString(5, task.getNotes());
      stmt.setDate(6, new Date(task.getDeadline().getTime()));
      stmt.setDate(7, new Date(task.getCreatedAt().getTime()));
      stmt.setDate(8, new Date(task.getUpdatedAt().getTime()));
      stmt.setInt(9, task.getId());
      stmt.execute();
    } catch (Exception ex) {
      throw new RuntimeException("Error updating task", ex);
    } finally {
      ConnectionFactory.closeConnection(conn, stmt);
    }

  }

  public void delete(int taskId) {
    String sql = "DELETE FROM tasks WHERE id = ?";

    Connection conn = null;
    PreparedStatement stmt = null;

    try {
      conn = ConnectionFactory.getConnection();
      stmt = conn.prepareStatement(sql);
      stmt.setInt(1, taskId);
      stmt.execute();
    } catch (Exception ex) {
      throw new RuntimeException("Error deleting task", ex);
    } finally {
      ConnectionFactory.closeConnection(conn, stmt);
    }
  }

  public List<Task> findAll(int idProject) {
    String sql = "SELECT * FROM tasks WHERE idProject = ?";

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet resultSet = null;

    List<Task> tasks = new ArrayList<Task>();

    try {
      conn = ConnectionFactory.getConnection();
      stmt = conn.prepareStatement(sql);
      stmt.setInt(1, idProject);
      resultSet = stmt.executeQuery();

      while (resultSet.next()) {
        Task task = new Task();
        task.setId(resultSet.getInt("id"));
        task.setIdProject(resultSet.getInt("idProject"));
        task.setName(resultSet.getString("name"));
        task.setDescription(resultSet.getString("description"));
        task.setNotes(resultSet.getString("notes"));
        task.setCompleted(resultSet.getBoolean("status"));
        task.setDeadline(resultSet.getDate("deadline"));
        task.setCreatedAt(resultSet.getDate("createdAt"));
        task.setUpdatedAt(resultSet.getDate("updatedAt"));

        tasks.add(task);
      }
    } catch (Exception ex) {
      throw new RuntimeException("Error finding tasks", ex);
    } finally {
      ConnectionFactory.closeConnection(conn, stmt, resultSet);
    }

    return tasks;
  }
}
