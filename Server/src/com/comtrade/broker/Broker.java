package com.comtrade.broker;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JOptionPane;

import com.comtrade.SQLConnection.SQLConnection;
import com.comtrade.domain.Bill;
import com.comtrade.domain.Ingredients;
import com.comtrade.domain.RestaurantTable;
import com.comtrade.domain.Role;
import com.comtrade.domain.User;

public class Broker {

    public User returnRoleUser(User user) {
        String sql = "SELECT DISTINCT u.id_user, u.username, u.password, u.first_name as firstName,\n" +
                "r.role_name AS role\n" +
                "FROM users AS u INNER JOIN user_role_map AS ur ON ur.id_user = u.id_user \n" +
                "INNER JOIN role AS r ON r.id_role = ur.id_role \n" +
                "WHERE u.username = ? AND u.password = ?\n" +
                "LIMIT 1";
        try {
            PreparedStatement preparedStatement = SQLConnection.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            Set<Role> role = new HashSet<>();

            if (resultSet.next()) {
                Role userRole = new Role();
                userRole.setRole_name(resultSet.getString("role"));
                role.add(userRole);
                int id_user = resultSet.getInt("id_user");
                String userFirstName = resultSet.getString("firstName");
                user.setId_user(id_user);
                user.setFirst_name(userFirstName);
                user.setSetRoleUser(role);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void saveUser(User user) {
        int idUser = 0;
        ResultSet resultSet = null;
        String sql = "INSERT INTO users (first_name, username, password, status) VALUES (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = SQLConnection.getInstance().getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getFirst_name());
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.setString(3, user.getPassword());
            if(user.getRoleName().equalsIgnoreCase("Собственник")) {
            	preparedStatement.setInt(4, 1);
            }else {
                preparedStatement.setInt(4, 0);
            }
            int rowAffected = preparedStatement.executeUpdate();
            if (rowAffected != 0) {
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    idUser = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql1 = "INSERT INTO user_role_map(id_user, id_role) VALUES (?,?)";
        try {
            PreparedStatement preparedStatement = SQLConnection.getInstance().getConnection().prepareStatement(sql1);
            preparedStatement.setInt(1, idUser);
            if(user.getRoleName().equalsIgnoreCase("Собственник")) {
            	 preparedStatement.setInt(2, 1);
            }else {
                preparedStatement.setInt(2, 3);
            }

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql2 = "INSERT INTO user_restaurant_map(id_restaurant, id_user) VALUES (?,?)";
        try {
            PreparedStatement preparedStatement = SQLConnection.getInstance().getConnection().prepareStatement(sql2);

            preparedStatement.setInt(1, 1);
            preparedStatement.setInt(2, idUser);

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editUser(User user) {
        int idUser = 0;
        ResultSet resultSet = null;
        String sql = "UPDATE users SET first_name=?, status=? WHERE username=?";
        try {
            PreparedStatement preparedStatement = SQLConnection.getInstance().getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getFirst_name());
            if(user.getRoleName().equalsIgnoreCase("Собственник")) {
                preparedStatement.setInt(2, 1);
            }else {
                preparedStatement.setInt(2, 0);
            }
            preparedStatement.setString(3, user.getUserName());
            int rowAffected = preparedStatement.executeUpdate();
            if (rowAffected != 0) {
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    idUser = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql1 = "UPDATE user_role_map SET id_role=? WHERE id_user=?";
        try {
            PreparedStatement preparedStatement = SQLConnection.getInstance().getConnection().prepareStatement(sql1);
            preparedStatement.setInt(2, idUser);
            if(user.getRoleName().equalsIgnoreCase("Собственник")) {
                preparedStatement.setInt(1, 1);
            }else {
                preparedStatement.setInt(1, 3);
            }
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(User user) {
        int idUser = 0;
        String sql = "SELECT DISTINCT id_user FROM users WHERE username=?;";
        try {
            PreparedStatement preparedStatement = SQLConnection.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setString(1, user.getUserName());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                idUser = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql1 = "DELETE FROM users WHERE id_user=?";
        try {
            PreparedStatement preparedStatement = SQLConnection.getInstance().getConnection().prepareStatement(sql1);
            preparedStatement.setInt(1, idUser);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql2 = "DELETE FROM user_role_map WHERE id_user=?";
        try {
            PreparedStatement preparedStatement = SQLConnection.getInstance().getConnection().prepareStatement(sql2);
            preparedStatement.setInt(1, idUser);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql3 = "DELETE FROM user_restaurant_map WHERE id_user=?";
        try {
            PreparedStatement preparedStatement = SQLConnection.getInstance().getConnection().prepareStatement(sql3);
            preparedStatement.setInt(1, idUser);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> returnUsers(int idOwner) {

        List<User>list = new ArrayList<>();

        User user = null;

       String sql = "SELECT DISTINCT first_name as first_name,username as user_name, \n" +
               "role.id_role as id_role, role.role_name as role_name, restaurants.id_restaurant \n" +
               "as id_restaurant, restaurants.name_restaurant  as name_restaurant\n" +
               "FROM users INNER JOIN user_restaurant_map ON users.id_user = user_restaurant_map.id_user \n" +
               "INNER JOIN restaurants ON restaurants.id_restaurant = user_restaurant_map.id_restaurant\n" +
               "INNER JOIN user_role_map ON user_role_map.id_user = users.id_user\n" +
               "INNER JOIN role ON role.id_role = user_role_map.id_role";

        try {
            PreparedStatement preparedStatement = SQLConnection.getInstance().getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            	
                user = new User(resultSet.getString("first_name"),resultSet.getString("user_name"),
                		resultSet.getInt("id_role"), resultSet.getString("role_name"), resultSet.getInt("id_restaurant"),
                        resultSet.getString("name_restaurant"));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public List<Ingredients> getIngredients(int id_ingredient) {
        List<Ingredients> listIngredients = new ArrayList<>();
        Ingredients ingredients = null;
        String sql = "select existing_ingredients.quantity, ingredients.id_ingredient, ingredients.price,"
        		+ " ingredients.ingredient_name, ingredients.measurement_quantity from existing_ingredients\r\n" + 
        		"inner join\r\n" + 
        		"ingredients on existing_ingredients.id_ingredient = ingredients.id_ingredient";

        try {
            PreparedStatement preparedStatement = SQLConnection.getInstance().getConnection().prepareStatement(sql);
            //preparedStatement.setInt(1, id_ingredient);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ingredients = new Ingredients(resultSet.getInt("id_ingredient"), resultSet.getString("ingredient_name"),
                        resultSet.getDouble("price"), resultSet.getString("measurement_quantity"), resultSet.getDouble("quantity"));
                listIngredients.add(ingredients);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listIngredients;
    }

	public void addIngredient(Ingredients ingredient) {
		 int idIngredient = 0;
	        ResultSet resultSet = null;
	        String sql = "INSERT INTO ingredients (price, ingredient_name, measurement_quantity) VALUES (?,?,?)";
	        try {
	            PreparedStatement preparedStatement = SQLConnection.getInstance().getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	            preparedStatement.setDouble(1, ingredient.getprice());
	            preparedStatement.setString(2, ingredient.getIngredient_name());
	            preparedStatement.setString(3, ingredient.getQuantity_measure());
	            int rowAffected = preparedStatement.executeUpdate();
	            if (rowAffected != 0) {
	                resultSet = preparedStatement.getGeneratedKeys();
	                if (resultSet.next()) {
	                    idIngredient = resultSet.getInt(1);
	                }
	            }
	        } catch (SQLException e) {
                System.out.println("Its sad");
                e.printStackTrace();
	        }
	        String sql1 = "INSERT INTO existing_ingredients (id_ingredient,id_restaurant, quantity) VALUES (?,?,?)";
	        try {
	        	 int idExistingIngredient = 0;
	 	        ResultSet resultSet2 = null;
	            PreparedStatement preparedStatement = SQLConnection.getInstance().getConnection().prepareStatement(sql1, PreparedStatement.RETURN_GENERATED_KEYS);
	            preparedStatement.setInt(1, idIngredient);
	            preparedStatement.setInt(2, 1);
	            preparedStatement.setDouble(3, ingredient.getQuantity());
	            
	            int rowAffected = preparedStatement.executeUpdate();
	            if (rowAffected != 0) {
	                resultSet2 = preparedStatement.getGeneratedKeys();
	                if (resultSet2.next()) {
	                	idExistingIngredient = resultSet2.getInt(1);
	                }
	            }
	        } catch (SQLException e) {
                e.printStackTrace();
	        }
	}

    public void saveRestaurantTable(RestaurantTable restaurantTable) {
        int id_restaurant_table = 0;
        ResultSet resultSet = null;
        List<Integer> restaurantTable1 = new CopyOnWriteArrayList<Integer>();
        String sql = "INSERT INTO restaurant_table (table_number, id_restaurant, table_status) VALUES (?,?,?)";
        String sql1 = "select restaurant_table.table_number from restaurant_table";
        try {
            PreparedStatement preparedStatement1 = SQLConnection.getInstance().getConnection().prepareStatement(sql1);
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            while (resultSet1.next()) {

                restaurantTable1.add(resultSet1.getInt("table_number"));
            }
            PreparedStatement preparedStatement = SQLConnection.getInstance().getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            if(!restaurantTable1.contains(restaurantTable.getTable_number())) {
                preparedStatement.setInt(1, restaurantTable.getTable_number());
                preparedStatement.setInt(2, 1);
                preparedStatement.setInt(3, 0);
                int rowAffected = preparedStatement.executeUpdate();
                if (rowAffected != 0) {
                    resultSet = preparedStatement.getGeneratedKeys();
                    if (resultSet.next()) {
                        id_restaurant_table = resultSet.getInt(1);
                    }
                }
            }else {
                JOptionPane.showMessageDialog(null, "Table with this number already exists, please try a different one");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	public List<Integer> returnAllTableNumbers(RestaurantTable restaurantTable) {
		List<Integer> tablez  = new ArrayList<>();
        String sql = "select restaurant_table.table_number from restaurant_table";
        try {
            PreparedStatement preparedStatement = SQLConnection.getInstance().getConnection().prepareStatement(sql);
            //preparedStatement.setInt(1, restaurantTable.getTable_number());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            	tablez.add(resultSet.getInt("table_number"));
            }
           
            if (resultSet.next()) {
            	restaurantTable = new RestaurantTable(resultSet.getInt("table_number"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tablez;
	}
	  public List<Ingredients> getIngredientsWithQuantityBiggerThanZero(int id_ingredient) {
	        List<Ingredients> listIngredients = new ArrayList<>();
	        Ingredients ingredients = null;
	        String sql = "select existing_ingredients.quantity, ingredients.id_ingredient, ingredients.price,"
	        		+ " ingredients.ingredient_name, ingredients.measurement_quantity from existing_ingredients\r\n" +
	        		"inner join\r\n" +
	        		"ingredients on existing_ingredients.id_ingredient = ingredients.id_ingredient WHERE existing_ingredients.quantity > 0";

	        try {
	            PreparedStatement preparedStatement = SQLConnection.getInstance().getConnection().prepareStatement(sql);
	            ResultSet resultSet = preparedStatement.executeQuery();
	            while (resultSet.next()) {
	                ingredients = new Ingredients(resultSet.getInt("id_ingredient"), resultSet.getString("ingredient_name"),
	                        resultSet.getDouble("price"), resultSet.getString("measurement_quantity"), resultSet.getDouble("quantity"));

	                listIngredients.add(ingredients);

	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return listIngredients;
	    }

    public Object updateIngredients(Ingredients ingredient) {
        String sql = "update existing_ingredients \n" +
                "SET quantity = ?" +
                "WHERE existing_ingredients.id_ingredient= ?;";
        try {
            PreparedStatement preparedStatement = SQLConnection.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setDouble(1,ingredient.getQuantity());
            preparedStatement.setInt(2, ingredient.getId_ingredient());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingredient;
    }

	public void saveBill(Bill bill) throws SQLException {
	     int idBill = 0;
	        ResultSet resultSet = null;
	        bill.setOrderList("a");
	        String sql = "INSERT INTO bill (table_number, bill_time, id_restaurant, waiterName, finalAmount) VALUES (?,?,?,?,?)";
	        try {
	            PreparedStatement preparedStatement = SQLConnection.getInstance().getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	            preparedStatement.setInt(1, bill.getid_restaurantTable());
	            preparedStatement.setString(2, (bill.getDateTime()).toString());
	            preparedStatement.setInt(3, bill.getIdRestaurant());
	            preparedStatement.setString(4, bill.getWaiterName());
	            preparedStatement.setDouble(5, bill.getFinalAmount());
	            int rowAffected = preparedStatement.executeUpdate();
	           if (rowAffected != 0) {
	               resultSet = preparedStatement.getGeneratedKeys();
	                if (resultSet.next()) {
	                 idBill = resultSet.getInt(1);
	                }
	            }
	        } catch (SQLException e) {
	        	System.out.println(e.getMessage());
	        	e.printStackTrace();
	        }
	        String sql1 = "INSERT INTO bill_orders_map (id_bill, id_order) VALUES (?,?)";
	        try {
	            PreparedStatement preparedStatement1 = SQLConnection.getInstance().getConnection().prepareStatement(sql1);
	            preparedStatement1.setInt(1, idBill);
	            preparedStatement1.setInt(2, 2);
	            preparedStatement1.execute();
		
	} finally {
		try {
			if(resultSet!=null) resultSet.close();
		}catch (SQLException em){
			System.out.println(em.getMessage());
			em.printStackTrace();
		}
	}
	}

	    public List<Bill> returnAllBills(int idBill) {

        List<Bill>list = new ArrayList<>();

        Bill bill = null;
       String sql = "Select * from bill";

        try {
            PreparedStatement preparedStatement = SQLConnection.getInstance().getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            	
            	bill = new Bill(resultSet.getInt("table_number"),resultSet.getString("bill_time"),
                		resultSet.getInt("id_restaurant"), resultSet.getString("waiterName"), resultSet.getDouble("finalAmount"));
                list.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
	
	
	
