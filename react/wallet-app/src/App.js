import Nav from './components/Nav/Nav';
import Welcome from './components/Welcome';
import { Routes, Route } from "react-router-dom"
import Dashboard from './components/Dashboard/Dashboard';
import CreateWallet from './components/Dashboard/Dashboard-Operations/CreateWallet';
import NotFound from './components/NotFound';
import UpdateWallet from './components/Dashboard/Dashboard-Operations/UpdateWallet';
import Transaction from './components/Transactions/Transaction';
import AddTransaction from './components/Transactions/Transaction-Operations/AddTransaction';

function App() {
  return (
    <div>
      <Nav />
      <Routes>
        <Route path='/' element={<Welcome/>} />
        <Route path='/dashboard' element={<Dashboard/>} />
        <Route path='/create-wallet' element={<CreateWallet/>} />
        <Route path='/update-wallet/:walletId' element={<UpdateWallet/>} />
        <Route path='/transactions/:walletId' element={<Transaction/>} />
        <Route path='/transactions/add-transaction/:walletId' element={<AddTransaction/>} />
        <Route path='*' element={<NotFound/>} />
      </Routes>
      
    </div>
  );
}

export default App;
