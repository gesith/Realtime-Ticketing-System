import React, { Component } from "react";

class CustomerTaskForm extends Component {
  constructor(props) {
    super(props);
    this.state = {
      ticketsToRemove: "",
      availableTickets: null,
      message: "",
    };
  }

  // Fetch the total number of available tickets
  fetchAvailableTickets = async () => {
    try {
      const response = await fetch("http://localhost:8080/api/customer/available-tickets");
      if (response.ok) {
        const data = await response.json();
        this.setState({ availableTickets: data }); // Update the state with the total tickets
      } else {
        this.setState({ message: "Failed to fetch available tickets." });
      }
    } catch (error) {
      this.setState({ message: "Error: Could not connect to the server." });
    }
  };

  // Start polling for ticket updates
  componentDidMount() {
    this.fetchAvailableTickets(); // Initial fetch
    this.pollingInterval = setInterval(this.fetchAvailableTickets, 5000); // Poll every 5 seconds
  }

  componentWillUnmount() {
    clearInterval(this.pollingInterval);
  }

  handleChange = (e) => {
    const { value } = e.target;
    const positiveInteger = value.replace(/[^0-9]/g, ""); 
    this.setState({ ticketsToRemove: positiveInteger });
  };

  // Handle form submission
  handleSubmit = async (e) => {
    e.preventDefault();

    const { ticketsToRemove } = this.state;

    try {
      const response = await fetch(
        `http://localhost:8080/api/customer/start-customer-task?ticketsToRemove=${ticketsToRemove}`,
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
    const { ticketsToRemove, availableTickets, message } = this.state;

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
            <h2>Start Buying</h2>

            {/* Display the total number of available tickets */}
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
                Total Available Tickets: {availableTickets}
              </div>
            )}

            <input
              type="number"
              name="ticketsToRemove"
              placeholder="Number of tickets to buy"
              value={ticketsToRemove}
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
              Continue
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

export default CustomerTaskForm;

