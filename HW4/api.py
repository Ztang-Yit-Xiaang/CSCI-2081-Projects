import csv
import os
from flask import Flask, redirect, url_for, request
from flask import jsonify


app = Flask(__name__)

# ******************************************
# Particle Simulation
# ******************************************

# load from CSV
obstacles = [] #maybe use a dictionary?

def load_obstacles_from_csv(csv_file):
    global obstacles
    try:
        with open(csv_file, mode='r', newline='', encoding='utf-8') as file:
            reader = csv.DictReader(file)
            obstacles = [
                {
                    "x_val": float(row["x_val"]),
                    "y_val": float(row["y_val"]),
                    "radius": float(row["radius"])
                }
                for row in reader
            ]
        print(f"Loaded {len(obstacles)} obstacles from {csv_file}.")
    except FileNotFoundError:
        print(f"File '{csv_file}' not found. Starting with an empty obstacle list.")
    except Exception as e:
        print(f"Error loading obstacles: {e}")
csv_file_path = "obstacles.csv"
load_obstacles_from_csv(csv_file_path)

@app.route('/obstacle', methods=['POST'])
def add_obstacle():
    # add to the obstacles (request.json)
    # save obstacles to csv
    obstacle_click = request.json
    ###obstacle_click['id'] = len(obstacles)
    obstacles.append(obstacle_click)
        # Define the CSV file name
    csv_file = "obstacles.csv"

    # Check if the file already exists to write headers
    write_header = not os.path.exists(csv_file)

    # Open the CSV file in append mode and write the new obstacle
    with open(csv_file, mode='a', newline='', encoding='utf-8') as file:
        writer = csv.DictWriter(file, fieldnames=obstacle_click.keys())

        # Write headers only if the file is new
        if write_header:
            writer.writeheader()

        # Write the obstacle data
        writer.writerow(obstacle_click)
    return "Success"

@app.route('/obstacle/<mouseX>/<mouseY>', methods=['DELETE'])
def delete_obstacle(mouseX,mouseY):
    # delete obstacle from obstacles (perhaps use an id)
    # save obstacles to csv
    ###print(id)
    # Find user and delete by id
    ###id  = int(id)
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
        obstacles.remove(obstacles[searched_act])

        # Reassign ids for remaining obstacles
        ###for i in range(searched_act, len(obstacles)):
            ###obstacles[i]["id"] = i

        csv_file = "obstacles.csv"
        with open(csv_file, mode='w', newline='', encoding='utf-8') as file:
            if obstacles:
                writer = csv.DictWriter(file, fieldnames=obstacles[0].keys())
                writer.writeheader()
                writer.writerows(obstacles)
            else:
                writer = csv.DictWriter(file, fieldnames=["id"])
                writer.writeheader()
    return "Success"

@app.route('/obstacles', methods=['GET'])
def get_obstacles():

    return jsonify(obstacles)



# ******************************************
# Mouse Clicks from Processing
# ******************************************

mouse_clicks = []

@app.route('/mouse_click', methods=['POST'])
def add_mouse_click():
    mouse_click = request.json
    mouse_clicks.append(mouse_click)
    return "Success"

@app.route('/mouse_clicks', methods=['GET'])
def get_mouse_clicks():
    return jsonify(mouse_clicks)

# ******************************************
# Route API
# ******************************************

routeArray = ["Route 1", "Route 2", "Route 3"]

@app.route('/route', methods=['post'])
def add_route():
    route = request.json
    print(route["name"])
    routeArray.append(route["name"])
    return "Successs"

@app.route('/route', methods=['DELETE'])
def delete_route():
    routeArray.remove(request.json["name"])
    return "Successs"

@app.route('/routes', methods=['GET','POST'])
def routes():
    return jsonify(routeArray)

# ******************************************
# Simple Call
# ******************************************

@app.route('/', methods=['GET'])
def hello_world():
    return 'Hello World'

if __name__ == '__main__':
    app.run(debug=True)