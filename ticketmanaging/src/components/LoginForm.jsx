import React, { Component } from "react";
import ConfigurationForm from "./ConfigurationForm"; 
class LoginForm extends Component {
  constructor(props) {
    super(props);
    this.state = {
      formData: {
        email: "",
        password: "",
        role: "customer",
        accountType: "normal", 
      },
      message: "",
      showConfigForm: false, 
    };
  }

  handleChange = (e) => {
    const { name, value } = e.target;
    this.setState({ formData: { ...this.state.formData, [name]: value } });
  };

  handleSubmit = async (e) => {
    e.preventDefault();

    const { formData } = this.state;

    const accType = formData.role === "customer" ? formData.accountType === "vip" : null;

    const requestData = {
      ...formData,
      accType, 
    };

    try {
      const response = await fetch("http://localhost:8080/api/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(requestData),
      });

      const result = await response.text(); 

      if (response.ok && result === "Login successful") {
        // Pass role and accType to parent onLoginSuccess
        this.props.onLoginSuccess(formData.role, accType);
        this.setState({ message: result || "Login successful!" });
      } else {
        this.setState({ message: result || "Login failed. Please check your credentials." });
      }
    } catch (error) {
      this.setState({ message: "An error occurred. Please try again later." });
    }
  };

  handleShowConfigForm = () => {
    this.setState({ showConfigForm: true }); // Set state to show the ConfigurationForm
  };

  render() {
    const { formData, message, showConfigForm } = this.state;

    return (
      <div style={{ display: "flex", height: "100vh" }}>
        <div style={{ flex: 1, backgroundColor: "#f0f0f0" }}>
        </div>
        <div style={{ flex: 1, display: "flex", flexDirection: "column", justifyContent: "center", alignItems: "center" }}>
          {!showConfigForm ? (
            <form onSubmit={this.handleSubmit} style={{ maxWidth: "400px", width: "100%", textAlign: "center" }}>
              <h2>Login</h2>
              <input
                type="email"
                name="email"
                placeholder="Email"
                value={formData.email}
                onChange={this.handleChange}
                required
                style={{ padding: "10px", margin: "10px 0", width: "100%" }}
              />
              <input
                type="password"
                name="password"
                placeholder="Password"
                value={formData.password}
                onChange={this.handleChange}
                required
                style={{ padding: "10px", margin: "10px 0", width: "100%" }}
              />
              <div style={{ margin: "10px 0" }}>
                <label>
                  <input
                    type="radio"
                    name="role"
                    value="customer"
                    checked={formData.role === "customer"}
                    onChange={this.handleChange}
                  />
                  Customer
                </label>
                <label>
                  <input
                    type="radio"
                    name="role"
                    value="vendor"
                    checked={formData.role === "vendor"}
                    onChange={this.handleChange}
                  />
                  Vendor
                </label>
              </div>

              {formData.role === "customer" && (
                <div style={{ margin: "10px 0" }}>
                  <label htmlFor="accountType">Account Type:</label>
                  <select
                    id="accountType"
                    name="accountType"
                    value={formData.accountType}
                    onChange={this.handleChange}
                    style={{ padding: "10px", marginLeft: "10px" }}
                  >
                    <option value="normal">Normal</option>
                    <option value="vip">VIP</option>
                  </select>
                </div>
              )}

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
                Login
              </button>
              {/* Link to switch to the registration form */}
              <p style={{ marginTop: "10px" }}>
                Don't have an account?{" "}
                <span onClick={this.props.onToggleForm} style={{ color: "blue", cursor: "pointer" }}>
                  Register here
                </span>
              </p>

              {/* Link to show the ConfigurationForm */}
              <p style={{ marginTop: "10px" }}>
                Want to see the configuration?{" "}
                <span onClick={this.handleShowConfigForm} style={{ color: "blue", cursor: "pointer" }}>
                  Click here
                </span>
              </p>
            </form>
          ) : (
            // Show ConfigurationForm when state is set to true
            <ConfigurationForm />
          )}

          {/* Display success or error message */}
          <p>{message}</p>
        </div>
      </div>
    );
  }
}

export default LoginForm;