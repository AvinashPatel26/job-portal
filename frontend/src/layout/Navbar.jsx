export default function Navbar() {
  return (
    <div className="h-16 bg-white border-b px-8 flex items-center justify-between">
      
      <input
        type="text"
        placeholder="Search jobs..."
        className="bg-gray-100 px-4 py-2 rounded-lg w-72 outline-none"
      />

      <div className="flex items-center gap-4">
        <div className="text-right">
          <p className="text-sm font-medium">Avinash</p>
          <p className="text-xs text-gray-500">Job Seeker</p>
        </div>

        <div className="w-10 h-10 bg-blue-500 rounded-full text-white flex items-center justify-center">
          A
        </div>
      </div>
    </div>
  );
}
