import React, { useState } from "react";
import LoginForm from "./components/LoginForm";
import RegisterForm from "./components/RegisterForm";
import VendorTaskForm from "./components/VendorTaskForm";
import CustomerTaskForm from "./components/CustomerTaskForm";
import VIPCustomerTaskForm from "./components/VIPCustomerTaskForm"; 

function App() {
  const [isLogin, setIsLogin] = useState(true); // true = show login form, false = show register form
  const [userRole, setUserRole] = useState(""); // Track user's role (customer or vendor)
  const [isLoggedIn, setIsLoggedIn] = useState(false); // Track if the user is logged in or registered successfully
  const [accType, setAccType] = useState(false); // Track account type (Normal = false, VIP = true)

  // Toggle between login and register forms
  const toggleForm = () => {
    setIsLogin(!isLogin);
  };

  // Handle login success and update the role and account type
  const handleLoginSuccess = (role, accType) => {
    setUserRole(role);
    setAccType(accType); // Set account type (Normal or VIP)
    setIsLoggedIn(true);
  };

  // Handle register success and update the role and account type
  const handleRegisterSuccess = (role, accType) => {
    setUserRole(role);
    setAccType(accType); // Set account type (Normal or VIP)
    setIsLoggedIn(true);
  };
  let formContent;
  if (!isLoggedIn) {
    formContent = isLogin ? (
      <LoginForm onLoginSuccess={handleLoginSuccess} onToggleForm={toggleForm} />
    ) : (
      <RegisterForm onRegisterSuccess={handleRegisterSuccess} onToggleForm={toggleForm} />
    );
  }

  return (
    <div>
      {isLoggedIn ? (
        userRole === "vendor" ? (
          <VendorTaskForm />
        ) : userRole === "customer" && accType ? ( // Render VIPCustomerTaskForm if accType is true
          <VIPCustomerTaskForm />
        ) : (
          <CustomerTaskForm /> // Render CustomerTaskForm if accType is false
        )
      ) : (
        formContent
      )}
    </div>
  );
}

export default App;