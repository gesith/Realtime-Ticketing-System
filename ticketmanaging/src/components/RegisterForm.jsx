import React from "react";

class RegisterForm extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      name: "",
      email: "",
      password: "",
      role: "customer", 
      accType: false, 
      message: "", 
    };
  }

  handleSubmit = async (e) => {
    e.preventDefault();

    const registrationData = {
      name: this.state.name,
      email: this.state.email,
      password: this.state.password,
      role: this.state.role,
      accType: this.state.role === "customer" && this.state.accType, // Send true for VIP customers, false otherwise
    };

    try {
      // Make the API request to register the user
      const response = await fetch("http://localhost:8080/api/register", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(registrationData),
      });

      if (response.ok) {
        this.setState({ message: "Registration successful!" });
        this.props.onRegisterSuccess(this.state.role, this.state.accType);
      } else {
        this.setState({ message: "Registration failed. Please try again." });
      }
    } catch (error) {
      this.setState({ message: "An error occurred. Please try again." });
    }
  };

  render() {
    return (
      <div style={{ display: "flex", height: "100vh" }}>
        {/* Left Section for Image or Design */}
        <div style={{ flex: 1, backgroundColor: "#f0f0f0" }}>
          {/* Placeholder for left image or design */}
        </div>

        {/* Right Section for Form */}
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
            <h2>Register</h2>

            {/* Input fields */}
            <input
              type="text"
              placeholder="Name"
              value={this.state.name}
              onChange={(e) => this.setState({ name: e.target.value })}
              required
              style={{ padding: "10px", margin: "10px 0", width: "100%" }}
            />
            <input
              type="email"
              placeholder="Email"
              value={this.state.email}
              onChange={(e) => this.setState({ email: e.target.value })}
              required
              style={{ padding: "10px", margin: "10px 0", width: "100%" }}
            />
            <input
              type="password"
              placeholder="Password"
              value={this.state.password}
              onChange={(e) => this.setState({ password: e.target.value })}
              required
              style={{ padding: "10px", margin: "10px 0", width: "100%" }}
            />

            {/* Role selection */}
            <div style={{ margin: "10px 0" }}>
              <label>
                <input
                  type="radio"
                  value="customer"
                  checked={this.state.role === "customer"}
                  onChange={(e) => this.setState({ role: e.target.value })}
                />
                Customer
              </label>
              <label style={{ marginLeft: "10px" }}>
                <input
                  type="radio"
                  value="vendor"
                  checked={this.state.role === "vendor"}
                  onChange={(e) => this.setState({ role: e.target.value })}
                />
                Vendor
              </label>
            </div>

            {/* Account type toggle (for customers only) */}
            {this.state.role === "customer" && (
              <div style={{ margin: "10px 0" }}>
                <label htmlFor="accType">Account Type:</label>
                <select
                  id="accType"
                  value={this.state.accType ? "vip" : "normal"}
                  onChange={(e) => this.setState({ accType: e.target.value === "vip" })}
                  style={{ padding: "10px", marginLeft: "10px" }}
                >
                  <option value="normal">Normal</option>
                  <option value="vip">VIP</option>
                </select>
              </div>
            )}

            {/* Submit button */}
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
              Register
            </button>

            {/* Link to toggle back to login */}
            <p style={{ marginTop: "10px" }}>
              Already have an account?{" "}
              <span
                onClick={this.props.onToggleForm}
                style={{ color: "blue", cursor: "pointer" }}
              >
                Login here
              </span>
            </p>
          </form>

          {/* Display success or error message */}
          {this.state.message && (
            <p style={{ marginTop: "20px", color: this.state.message.includes("failed") ? "red" : "green" }}>
              {this.state.message}
            </p>
          )}
        </div>
      </div>
    );
  }
}

export default RegisterForm;