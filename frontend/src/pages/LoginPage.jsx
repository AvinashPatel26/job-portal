import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import { loginAsync } from "../features/auth/authSlice";
import { Container, Card, Button, Form } from "react-bootstrap";

export default function LoginPage() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const dispatch = useDispatch();
  const navigate = useNavigate();

  const login = async (e) => {
    e.preventDefault();

    try {
      const token = await dispatch(
        loginAsync({ email, password })
      ).unwrap();

      localStorage.setItem("token", token);
      navigate("/dashboard");
    } catch {
      alert("Invalid credentials");
    }
  };
localStorage.setItem("token", res.data);
localStorage.setItem("userId", 1); // TEMP until JWT decode

  return (
    <Container className="d-flex vh-100 justify-content-center align-items-center">
      <Card className="p-4 shadow" style={{ width: 350 }}>
        <h3 className="text-center mb-3">Login</h3>

        <Form onSubmit={login}>
          <Form.Control
            placeholder="Email"
            className="mb-3"
            onChange={(e) => setEmail(e.target.value)}
          />

          <Form.Control
            type="password"
            placeholder="Password"
            className="mb-3"
            onChange={(e) => setPassword(e.target.value)}
          />

          <Button type="submit" className="w-100">
            Login
          </Button>
        </Form>
      </Card>
    </Container>
  );
}
