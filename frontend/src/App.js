import React, { useState, useEffect } from 'react';
import './App.css'; // Import the CSS file

function App() {
    const [tasks, setTasks] = useState([]);
    const [newTask, setNewTask] = useState('');
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        fetch('http://localhost:8080/api/tasks')
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                setTasks(data);
                setLoading(false);
            })
            .catch(error => {
                setError(error);
                setLoading(false);
            });
    }, []);

    const handleInputChange = (event) => {
        setNewTask(event.target.value);
    };

    const handleAddTask = () => {
        if (newTask.trim()) {
            fetch('http://localhost:8080/api/tasks', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ taskInfo: newTask }), // Adjust based on your backend's expected request body
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                return response.json();
            })
            .then(updatedTasks => {
                setTasks(updatedTasks);
                setNewTask('');
            })
            .catch(error => {
                console.error("Error adding task:", error);
                setError(error);
            });
        }
    };

    const handleCompleteTask = (taskNo) => {
        fetch(`http://localhost:8080/api/tasks/${taskNo}/complete`, {
            method: 'PUT',
        })
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json();
        })
        .then(updatedTasks => {
            setTasks(updatedTasks);
        })
        .catch(error => {
            console.error("Error completing task:", error);
            setError(error);
        });
    };

    const handleDeleteTask = (taskNo) => {
        fetch(`http://localhost:8080/api/tasks/${taskNo}`, {
            method: 'DELETE',
        })
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json();
        })
        .then(updatedTasks => {
            setTasks(updatedTasks);
        })
        .catch(error => {
            console.error("Error deleting task:", error);
            setError(error);
        });
    };

    if (loading) {
        return <div>Loading tasks...</div>;
    }

    if (error) {
        return <div>Error: {error.message}</div>;
    }

    return (
        <div className="App">
            <h1>Task List</h1>
            <div className="add-task">
                <input
                    type="text"
                    placeholder="Add new task"
                    value={newTask}
                    onChange={handleInputChange}
                />
                <button onClick={handleAddTask}>Add Task</button>
            </div>
            <ul>
                {tasks.map(task => (
                    <li key={task.taskNo} className={task.taskDone ? 'completed' : ''}>
                        <span>{task.taskInfo} (ID: {task.taskNo})</span>
                        <div className="actions">
                            <button onClick={() => handleCompleteTask(task.taskNo)}>
                                {task.taskDone ? 'Undo' : 'Complete'}
                            </button>
                            <button onClick={() => handleDeleteTask(task.taskNo)}>Delete</button>
                        </div>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default App;