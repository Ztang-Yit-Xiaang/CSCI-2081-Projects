
from flask import Flask, redirect, url_for, request
from flask import jsonify
import sqlite3

app = Flask(__name__)

# load from Database
obstacles = [] #maybe use a dictionary?
def load_obstacles_from_db():
    conn = sqlite3.connect('D:/UMN Couses/CSCI 2081 (001) Introduction to Software Development/Practice/HW5/obstacles.db')
    cursor = conn.cursor()
    cursor.execute("SELECT x_val, y_val, radius FROM ObstaclesDB")
    rows = cursor.fetchall()
    conn.close()
    return [{"x_val": row[0], "y_val": row[1], "radius": row[2]} for row in rows]

obstacles = load_obstacles_from_db()

@app.route('/obstacle', methods=['POST'])
def add_obstacle():
    conn = sqlite3.connect('D:/UMN Couses/CSCI 2081 (001) Introduction to Software Development/Practice/HW5/obstacles.db')
    # add to the obstacles (request.json)
    # save obstacles to database
    obstacle_click = request.json
    obstacles.append(obstacle_click)
    conn.execute("INSERT INTO ObstaclesDB(x_val, y_val, radius) VALUES(?, ?, ?);", (obstacle_click["x_val"], obstacle_click["y_val"], obstacle_click["radius"],)) 
    # Update changes in the database
    conn.commit()
    # close the database connection
    conn.close()
    return "Success"

@app.route('/obstacle/<mouseX>/<mouseY>', methods=['DELETE'])
def delete_obstacle(mouseX,mouseY):
    conn = sqlite3.connect('D:/UMN Couses/CSCI 2081 (001) Introduction to Software Development/Practice/HW5/obstacles.db')
    # delete obstacle from obstacles (perhaps use an id)
    # Find user and update based
    mouseX = float(mouseX)
    mouseY = float(mouseY)
    searched_act = -1
    for i in range(len(obstacles)):
        is_in_circle = ((mouseX - obstacles[i]["x_val"])*(mouseX - obstacles[i]["x_val"]) + (mouseY - obstacles[i]["y_val"])*(mouseY - obstacles[i]["y_val"]))
        if obstacles[i]["radius"]*obstacles[i]["radius"] >= is_in_circle:
            searched_act = i
            break
    # If the obstacle is found, delete it
    if searched_act != -1:
        conn.execute("DELETE FROM ObstaclesDB WHERE x_val=? AND y_val=? AND radius=?;", (obstacles[searched_act]["x_val"], obstacles[searched_act]["y_val"], obstacles[searched_act]["radius"],)) 
        obstacles.remove(obstacles[searched_act])
        
    # Update changes in the database
    conn.commit()
    # close the database connection
    conn.close()


    return "Success"

@app.route('/obstacles', methods=['GET'])
def get_obstacles():

    return jsonify(obstacles)

@app.route('/', methods=['GET'])
def hello_world():
    return 'Hello World'

if __name__ == '__main__':
    app.run(debug=True)