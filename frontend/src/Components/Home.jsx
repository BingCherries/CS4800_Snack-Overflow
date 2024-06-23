
import BannerBackground from "../Images/home-banner-background.png";
import BannerImage from "../Images/home-group-pet.png";
import { FiArrowRight } from "react-icons/fi";

const Home = () => {
  return (
    <div className="home-container">
      <div className="home-banner-container">
        <div className="home-bannerImage-container">
          <img src={BannerBackground} alt="" />
    </div>
    <div className="home-text-section">
          <h1 className="primary-heading">
            <div>Track. Identify. Protect.</div>
          </h1>
          <p className="primary-text">
          Keep your pet's well-being front and center with our latest allergy tracking feature. Showcase your pet's health journey with our platform, ensuring you're always one-click away from managing their allergies effectively!
          </p>
          <button className="secondary-button">
            Track Now <FiArrowRight />{" "}
          </button>
        </div>
        <div className="home-image-section">
          <img src={BannerImage} alt="" />
        </div>
  
      </div>
    </div>
  );
};

export default Home;