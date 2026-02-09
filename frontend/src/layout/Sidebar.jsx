import { Link } from "react-router-dom";

export default function Sidebar() {
  return (
    <div className="w-64 bg-white border-r flex flex-col">
      
      <div className="h-16 flex items-center px-6 font-bold text-xl border-b">
        JobPortal
      </div>

      <nav className="flex-1 px-4 py-6 space-y-2 text-gray-600">
        <Link className="block px-4 py-2 rounded-lg hover:bg-gray-100">
          Dashboard
        </Link>

        <Link className="block px-4 py-2 rounded-lg hover:bg-gray-100">
          Jobs
        </Link>

        <Link className="block px-4 py-2 rounded-lg hover:bg-gray-100">
          Applications
        </Link>

        <Link className="block px-4 py-2 rounded-lg hover:bg-gray-100">
          Profile
        </Link>
      </nav>

      <div className="p-4 border-t text-sm text-gray-500">
        © 2026 JobPortal
      </div>
    </div>
  );
}
