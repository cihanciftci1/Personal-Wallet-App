import React from 'react'
import { Link } from 'react-router-dom'

export default function Welcome() {
    return (
        <div className="landing">
            <div className="light-overlay landing-inner text-dark">
                <div className="container">
                    <div className="row">
                        <div className="col-md-12 text-center">
                            <h1 className="display-3 mb-4">Personal Expense Manager</h1>
                            <p className="lead">
                                Create your account to manage your daily expense.
                            </p>
                            <hr />
                            <Link to={"/dashboard"} className="btn btn-lg btn-primary mr-2">
                                Go to Dashboard
                            </Link>
                            
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}
