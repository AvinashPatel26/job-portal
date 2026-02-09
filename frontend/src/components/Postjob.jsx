import { useState } from "react";
import api from "../api/axios";

export default function PostJob() {
  const [job,setJob] = useState({});

  const post = async () => {
    await api.post("/jobs",job);
    alert("Job posted");
  };

  return (
    <div>
      <h2>Post Job</h2>
      <input placeholder="Title" onChange={e=>setJob({...job,title:e.target.value})}/>
      <input placeholder="Company" onChange={e=>setJob({...job,company:e.target.value})}/>
      <input placeholder="Location" onChange={e=>setJob({...job,location:e.target.value})}/>
      <textarea placeholder="Description" onChange={e=>setJob({...job,description:e.target.value})}/>
      <button onClick={post}>Post</button>
    </div>
  );
}
