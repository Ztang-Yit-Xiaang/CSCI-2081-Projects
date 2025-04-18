import sqlite3

# Open a database
conn = sqlite3.connect('obstacles.db')
cursor = conn.cursor()
name = cursor.execute("CREATE TABLE IF NOT EXISTS DanOrban (x_val DOUBLE, y_val DOUBLE, radius DOUBLE);")

conn.execute("INSERT INTO DanOrban(x_val, y_val, radius) VALUES(0.0, 1.0, 0.2);") 
print(cursor.execute("SELECT * from DanOrban;"))
rows = cursor.fetchall()
for row in rows:
    print(row)

# # Update
# conn.execute("update customer set FirstName = 'bob' where customerId = 2")

# # Delete
# conn.execute("delete from customer where customerId = 5")

# # Insert new Customer 5
# name = input("Enter First Name: ")
# conn.execute("INSERT INTO Customer(CustomerId, FirstName, LastName, Email) VALUES(5, ?, 'User', 'cool@example.com')", (name,))

# # Update changes in the database
conn.commit()

# # Query
# cursor = conn.execute("select customerId, FirstName, LastName from customer limit 10")
# rows = cursor.fetchall()
# for row in rows:
#     print(row)

# # Example of a join
# cursor = conn.execute("select p.Name as Playlist, t.Name as Track from Playlist p "
#                       "inner join PlaylistTrack pt "
#                       "on p.PlaylistId = pt.PlaylistId "
#                       "inner join Track t "
#                       "on pt.TrackId = t.TrackId "
#                       "WHERE p.PlaylistId = 12")
# rows = cursor.fetchall()
# for row in rows:
#     print(row)


# close the database connection
conn.close()