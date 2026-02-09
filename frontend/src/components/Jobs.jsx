import { useEffect,useState } from "react";
import api from "../api/axios";

export default function Jobs() {
  const [jobs,setJobs] = useState([]);

  useEffect(()=>{
    api.get("/jobs").then(res=>setJobs(res.data));
  },[]);

  return (
    <div>
      <h2>Job Listings</h2>
      {jobs.map(j=>(
        <div key={j.id}>
          <h3>{j.title}</h3>
          <p>{j.company} - {j.location}</p>
        </div>
      ))}
    </div>
  );
}
