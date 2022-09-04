import { Link } from 'react-router-dom';
import "../../styles/NavBar.css";

export default function ProviderNavBar() {
    return (
        <nav>
            <ul>
                <li>
                    <Link className="NavBarLink" to="/provider/claim">Claim</Link>
                </li>
                <li>
                    <Link className="NavBarLink" to="/provider/invoice">Invoice</Link>
                </li>
            </ul>
        </nav>
    );
}