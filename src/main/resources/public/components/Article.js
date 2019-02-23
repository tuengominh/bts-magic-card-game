class Article extends React.Component {
    render() {
    return
        <Title text={this.props.title} />;
        <p>{this.props.text}</p>;
        <Link text="Google" url="http://www.google.com" />;
    }
}