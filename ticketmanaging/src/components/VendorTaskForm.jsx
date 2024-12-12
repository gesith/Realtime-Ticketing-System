import React, { Component } from "react";

class VendorTaskForm extends Component {
  constructor(props) {
    super(props);
    this.state = {
      ticketsToAdd: "", 
      availableTickets: null, 
      maxTicketCapacity: null, 
      message: "", 
    };
  }

  // Fetch available tickets from the backend
  fetchAvailableTickets = async () => {
    try {
      const response = await fetch("http://localhost:8080/api/vendor/available-tickets");
      if (response.ok) {
        const data = await response.json();
        this.setState({ availableTickets: data }); // Update the state with available tickets
      } else {
        this.setState({ message: "Failed to fetch available tickets." });
      }
    } catch (error) {
      this.setState({ message: "Error: Could not connect to the server." });
    }
  };

  // Fetch max ticket capacity from the backend
  fetchMaxTicketCapacity = async () => {
    try {
      const response = await fetch("http://localhost:8080/api/vendor/max-tickets");
      if (response.ok) {
        const data = await response.json();
        this.setState({ maxTicketCapacity: data });
      } else {
        this.setState({ message: "Failed to fetch max ticket capacity." });
      }
    } catch (error) {
      this.setState({ message: "Error: Could not connect to the server." });
    }
  };

  componentDidMount() {
    this.fetchAvailableTickets(); 
    this.fetchMaxTicketCapacity(); 

    this.pollingInterval = setInterval(() => {
      this.fetchAvailableTickets();
      this.fetchMaxTicketCapacity();
    }, 5000); // Poll every 5 seconds
  }

  componentWillUnmount() {
    clearInterval(this.pollingInterval);
  }

  // Handle input field changes
  handleChange = (e) => {
    const { value } = e.target;

    // Ensure only positive integers are allowed
    const positiveInteger = value.replace(/[^0-9]/g, ""); // Remove non-numeric input
    this.setState({ ticketsToAdd: positiveInteger });
  };

  handleSubmit = async (e) => {
    e.preventDefault();

    const { ticketsToAdd } = this.state;

    try {
      const response = await fetch(
        `http://localhost:8080/api/vendor/start-vendor-task?ticketsToAdd=${ticketsToAdd}`,
        {
          method: "POST",
        }
      );

      const result = await response.text();
      this.setState({ message: result });
      this.fetchAvailableTickets(); 
    } catch (error) {
      this.setState({ message: "Error: Could not connect to the server." });
    }
  };

  render() {
    const { ticketsToAdd, availableTickets, maxTicketCapacity, message } = this.state;

    return (
      <div style={{ display: "flex", height: "100vh" }}>
        <div style={{ flex: 1, backgroundColor: "#f0f0f0" }}></div>
        <div
          style={{
            flex: 1,
            display: "flex",
            flexDirection: "column",
            justifyContent: "center",
            alignItems: "center",
          }}
        >
          <form
            onSubmit={this.handleSubmit}
            style={{ maxWidth: "400px", width: "100%", textAlign: "center" }}
          >
            <h2>Add Tickets</h2>
            {maxTicketCapacity !== null && (
              <div
                style={{
                  padding: "10px",
                  margin: "10px 0",
                  backgroundColor: "#D9EAFB",
                  color: "#0056b3",
                  borderRadius: "4px",
                  textAlign: "center",
                  fontWeight: "bold",
                  fontSize: "14px",
                }}
              >
                Maximum Ticket Capacity: {maxTicketCapacity}
              </div>
            )}
            {availableTickets !== null && (
              <div
                style={{
                  padding: "10px",
                  margin: "10px 0",
                  backgroundColor: "#D9EAFB",
                  color: "#0056b3",
                  borderRadius: "4px",
                  textAlign: "center",
                  fontWeight: "bold",
                  fontSize: "14px",
                }}
              >
                Available Tickets: {availableTickets}
              </div>
            )}
            <input
              type="number"
              name="ticketsToAdd"
              placeholder="Tickets to Add"
              value={ticketsToAdd}
              onChange={this.handleChange}
              required
              min="1" 
              style={{
                padding: "10px",
                margin: "10px 0",
                width: "100%",
                boxSizing: "border-box",
              }}
            />

            <button
              type="submit"
              style={{
                padding: "10px 20px",
                margin: "10px 0",
                width: "100%",
                backgroundColor: "#007BFF",
                color: "white",
                border: "none",
                borderRadius: "4px",
                cursor: "pointer",
              }}
            >
              Add Tickets
            </button>
          </form>

          {message && (
            <p
              style={{
                marginTop: "20px",
                color: message.includes("Error") ? "red" : "green",
              }}
            >
              {message}
            </p>
          )}
        </div>
      </div>
    );
  }
}

export default VendorTaskForm;
