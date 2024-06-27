import BannerBackground from "../Images/paw-print.png";
import BannerImage from "../Images/pet.png";
import { FiArrowRight } from "react-icons/fi";
import { useNavigate } from "react-router-dom";
const Home = () => {

  const navigate = useNavigate();

  const handleTrackNowClick = () => {
    navigate("/login");
  };

  return (
    <div className="home-container">
      <div className="home-banner-container">
        <div className="home-bannerImage-container">
        <img src={BannerBackground} alt="" />
    </div>
    <div className="home-full-txt">
    <div className="home-text-section">
          <h1 className="primary-heading">
            <div className="home-title">Track. Identify. Protect.</div>
          </h1>
  
          <p className="primary-text">
          Keep your pet's well-being front and center with our latest allergy tracking feature. Showcase your pet's health journey with our platform, ensuring you're always one-click away from managing their allergies effectively!
          </p>

          <button className="secondary-button" onClick={handleTrackNowClick}>
            Track Now <FiArrowRight />{" "}
          </button>
        </div>
        </div>
        <div className="home-image-section">
          <img src={BannerImage} alt="" />
        </div>
  
      </div>
    </div>
  );
};

export default Home;