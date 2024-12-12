import React, { Component } from "react";

class ConfigurationForm extends Component {
  constructor(props) {
    super(props);
    this.state = {
      configuration: {
        maxTicketCapacity: "",
        customerRetrievalRate: "",
        ticketReleaseRate: "",
        totalNumberTickets: "",
      },
      newConfig: {
        maxTicketCapacity: "",
        customerRetrievalRate: "",
        ticketReleaseRate: "",
        totalNumberTickets: "",
      },
      message: "",
    };
  }

  // Fetch current configuration
  fetchConfiguration = async () => {
    try {
      const response = await fetch("http://localhost:8080/api/configuration");
      const data = await response.json();
      this.setState({
        configuration: {
          maxTicketCapacity: data.maxTicketCapacity,
          customerRetrievalRate: data.customerRetrievalRate,
          ticketReleaseRate: data.ticketReleaseRate,
          totalNumberTickets: data.totalNumberTickets,
        },
      });
    } catch (error) {
      console.error("Error fetching configuration.");
    }
  };

  componentDidMount() {
    this.fetchConfiguration();
  }

  // Handle input change for new configuration
  handleChange = (e) => {
    const { name, value } = e.target;
    this.setState((prevState) => ({
      newConfig: {
        ...prevState.newConfig,
        [name]: value,
      },
    }));
  };


  handleSubmit = async (e) => {
    e.preventDefault();
  
    try {
      const response = await fetch("http://localhost:8080/api/configuration", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(this.state.newConfig),
      });
  
      const result = await response.text();
      this.setState({ message: result }); 
  
      if (response.ok) {
        this.fetchConfiguration();
      }
    } catch (error) {
      this.setState({ message: "Error updating configuration. Please try again later." });
    }
  };
  

  render() {
    const { configuration, newConfig, message } = this.state;

    return (
      <div
        style={{
          display: "flex",
          flexDirection: "column",
          justifyContent: "center",
          alignItems: "center",
          height: "100vh",
          gap: "20px",
        }}
      >
      
        <h2 style={{ color: "#007BFF" }}>Current Configuration</h2>

        <div
          style={{
            display: "grid",
            gridTemplateColumns: "1fr 1fr",
            gap: "20px",
            width: "80%",
          }}
        >
          {Object.entries(configuration).map(([key, value]) => (
            <div
              key={key}
              style={{
                padding: "10px",
                backgroundColor: "#e6f7ff",
                textAlign: "center",
                borderRadius: "4px",
                border: "1px solid #b3d9ff",
                height: "60px",
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
              }}
            >
              <strong>{key.replace(/([A-Z])/g, " $1")}: </strong> {value}
            </div>
          ))}
        </div>

        <h2 style={{ color: "#007BFF" }}>Update the Configuration</h2>

        <form
          onSubmit={this.handleSubmit}
          style={{
            display: "grid",
            gridTemplateColumns: "1fr",
            gap: "20px",
            width: "80%",
          }}
        >
          <input
            type="number"
            name="maxTicketCapacity"
            placeholder="Max Ticket Capacity"
            value={newConfig.maxTicketCapacity}
            onChange={this.handleChange}
            required
            style={{
              padding: "10px",
              border: "1px solid #ccc",
              borderRadius: "4px",
            }}
          />
          <input
            type="number"
            name="customerRetrievalRate"
            placeholder="Customer Retrieval Rate"
            value={newConfig.customerRetrievalRate}
            onChange={this.handleChange}
            required
            style={{
              padding: "10px",
              border: "1px solid #ccc",
              borderRadius: "4px",
            }}
          />
          <input
            type="number"
            name="ticketReleaseRate"
            placeholder="Ticket Release Rate"
            value={newConfig.ticketReleaseRate}
            onChange={this.handleChange}
            required
            style={{
              padding: "10px",
              border: "1px solid #ccc",
              borderRadius: "4px",
            }}
          />
          <input
            type="number"
            name="totalNumberTickets"
            placeholder="Total Number of Tickets"
            value={newConfig.totalNumberTickets}
            onChange={this.handleChange}
            required
            style={{
              padding: "10px",
              border: "1px solid #ccc",
              borderRadius: "4px",
            }}
          />
          <button
            type="submit"
            style={{
              padding: "10px",
              backgroundColor: "#007BFF",
              color: "white",
              border: "none",
              borderRadius: "4px",
              cursor: "pointer",
            }}
          >
            Update Configuration
          </button>
        </form>

        {/* Message Display */}
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
    );
  }
}

export default ConfigurationForm;



