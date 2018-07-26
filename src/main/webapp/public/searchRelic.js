class Relic extends React.Component {
    constructor() {
        super();
        this.state;
    }

    render(){
        return (
          <div className="relicGrid">
            <div className="RelicName"></div>

            <div className="pic"></div>
            <div className="item"></div>
            <div className="percent"></div>

            <div className="pic"></div>
            <div className="item"></div>
            <div className="percent"></div>

            <div className="pic"></div>
            <div className="item"></div>
            <div className="percent"></div>

            <div className="pic"></div>
            <div className="item"></div>
            <div className="percent"></div>

            <div className="pic"></div>
            <div className="item"></div>
            <div className="percent"></div>

            <div className="pic"></div>
            <div className="item"></div>
            <div className="percent"></div>
          </div>
        );
    }
}

class SearchRelic extends React.Component {

    constructor() {
        super();
        this.state = {};
    }


    handleSubmit(event){
        event.preventDefault();
        axios.get("")
            .then(function(response) {
                console.log(response);
            })
            .catch(function(error) {
                console.log(error);
            });
    }

    handleClick(event){
        var change = event;
    }

    render() {
        const searches = ["All", "Relics", "Locations", "Parts", "Missions"];
        const searchRadio = searches.map(search => {
            return (
                <span key={search}>
                    <input type="radio" name="relicSearch" value={search} />
                    <label htmlFor="relicSearch">{search}</label>
                </span>
            );
        });
        const quality = ["Intact", "Exceptional", "Flawless", "Radiant"];
        const qualitySelection = quality.map(quality => {
            return (
                <span key={quality}>
                    <input type="radio" name="qualitySelect" value={quality} />
                    <label htmlFor="qualitySelect">{quality}</label>
                </span>
            );
        });
        return (
            <div>
                <div id="head">
                    <h2>Search By:</h2>
                    <form onSubmit={this.handleSubmit}>
                        {searchRadio}
                        <br />
                        <p>Keyword:</p>
                        <input type="search" name="keyword" />
                        <br />
                        <input type="submit" value="Search" id="btn" />
                    </form>
                </div>
                <form onChange={this.handleChange}>
                    {qualitySelection}
                </form>
            </div>
        );
    }
}

var mainElement = document.querySelector("Main");
ReactDOM.render(<SearchRelic />, mainElement);