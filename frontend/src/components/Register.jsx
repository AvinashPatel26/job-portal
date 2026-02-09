import { useState } from "react";
import api from "../api/axios";

export default function Register() {
  const [user, setUser] = useState({
    name: "",
    email: "",
    password: "",
    role: "JOB_SEEKER",
  });

  const register = async () => {
    try {
      await api.post("/auth/register", user);
      alert("Registered successfully");
    } catch (err) {
      alert("Registration failed");
      console.error(err);
    }
  };

  return (
    <div>
      <h2>Register</h2>

      <input
        placeholder="Name"
        onChange={(e) => setUser({ ...user, name: e.target.value })}
      />

      <input
        placeholder="Email"
        onChange={(e) => setUser({ ...user, email: e.target.value })}
      />

      <input
        type="password"
        placeholder="Password"
        onChange={(e) => setUser({ ...user, password: e.target.value })}
      />

      <select
        onChange={(e) => setUser({ ...user, role: e.target.value })}
      >
        <option value="JOB_SEEKER">JOB SEEKER</option>
        <option value="EMPLOYER">EMPLOYER</option>
      </select>

      <button onClick={register}>Register</button>
    </div>
  );
}
