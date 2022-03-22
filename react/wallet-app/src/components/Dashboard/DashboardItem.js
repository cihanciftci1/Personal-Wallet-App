import React from 'react'
import { confirmAlert } from 'react-confirm-alert';
import { useDispatch } from 'react-redux'
import { Link, useNavigate } from 'react-router-dom'
import { deleteWallet } from '../../redux/actions/walletActions';

export default function DashboardItem(props) {
    const navigate=useNavigate();
    const dispatch = useDispatch();
    function handleClick(walletId){
        confirmAlert({
            title: 'You are deleting a wallet!',
            message: 'Are you sure to do this?',
            buttons: [
                {
                    label: 'Yes',
                    onClick: ()=>dispatch(deleteWallet(walletId))
                },
                {
                    label: 'No',
                    onClick: () => navigate("/dashboard")
                }
            ]
        })        
    }
    return (
        <div className="container">
            <div className="card card-body bg-light mb-3">
                <div className="row" >
                    <div className="col-lg-3 col-md-3 col-6">
                        <h3>{props.wallet.name}</h3>
                        <p>Account Number: {props.wallet.accountNumber}</p>
                    </div>
                    <div className="col-lg-3 col-md-3 col-6 text-center">
                        <h3>Description</h3>
                        <h5>{props.wallet.description}</h5>
                    </div>
                    <div className="col-lg-3 col-md-3 col-6 text-center">
                        <h3>Balance</h3>
                        <h1>₺ {props.wallet.currentBalance}</h1>
                    </div>
                    <div className="col-md-3 col-12 d-lg-block">
                        <ul className="list-group">
                            <Link to={`/transactions/${props.wallet.id}`}>
                                <li className="list-group-item board text-success">
                                    <i className="fa fa-flag-checkered pr-1"> View Transactions </i>
                                </li>
                            </Link>
                            <Link to={`/update-wallet/${props.wallet.id}`} >
                                <li className="list-group-item update text-info">
                                    <i className="fa fa-edit pr-1"> Update Account Info</i>
                                </li>
                            </Link>
                            <Link to="/dashboard" onClick={() => handleClick(props.wallet.id)}>
                                <li className="list-group-item delete text-danger">
                                    <i className="fa fa-minus-circle pr-1"> Delete Account</i>
                                </li>
                            </Link>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    )
}
