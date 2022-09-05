import { Link } from 'react-router-dom';
import "../../styles/NavBar.css";

export default function ProviderNavBar() {
    return (
        <nav>
            <ul>
                <li>
                    <Link className="NavBarLink" to='/provider/claimPage'>Claim</Link>
                </li>
            </ul>
        </nav>
    );
}