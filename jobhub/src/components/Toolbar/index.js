import React from "react";
import { fade, makeStyles, withStyles } from "@material-ui/core/styles";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import IconButton from "@material-ui/core/IconButton";
import Typography from "@material-ui/core/Typography";
import InputBase from "@material-ui/core/InputBase";
import Badge from "@material-ui/core/Badge";
import MenuItem from "@material-ui/core/MenuItem";
import Menu from "@material-ui/core/Menu";
import MenuIcon from "@material-ui/icons/Menu";
import SearchIcon from "@material-ui/icons/Search";
import AccountCircle from "@material-ui/icons/AccountCircle";
import MailIcon from "@material-ui/icons/Mail";
import NotificationsIcon from "@material-ui/icons/Notifications";
import MoreIcon from "@material-ui/icons/MoreVert";
import axios from 'axios';
import { getCurrentUser } from '../../redux/selectors/userSelectors';
import SignOutButton from '../SignOut';
import * as ROUTES from '../../constants/routes';
import { Link } from 'react-router-dom'; 
import Button from '@material-ui/core/Button'; 
import { createSelector } from 'reselect'
import {connect, useDispatch, useSelector} from 'react-redux';
const styles = theme => ({
  grow: {
    flexGrow: 1
  },
  menuButton: {
    marginRight: theme.spacing(2)
  },
  title: {
    display: "none",
    [theme.breakpoints.up("sm")]: {
      display: "block"
    }
  },
  inputRoot: {
    color: "inherit"
  },
  sectionDesktop: {
    display: "none",
    [theme.breakpoints.up("md")]: {
      display: "flex"
    }
  },
});

class ToolbarComponent extends React.Component {
  state = {
    searchId: ''
  };

  onChangeSearch = event => {
    this.setState({ searchId: event.target.value });
    console.log(this.state.searchId);
    axios.get('http://localhost:8040/user/getTicketById/'+this.state.searchId, {
      "headers": {
        Accept: "application/json",
        "Content-Type": "application/json",
        "Access-Control-Allow-Origin": "*",
        "Upgrade-Insecure-Requests": "1",
      },
    })
      .then(function (response) {
        console.log("response from server",response.data)
        
      })
      .catch(function (error) {
        console.log(error);
      })
  };

  render() {
    const { classes } = this.props;

    return (
      <div className={classes.grow}>
        <AppBar position="static">
          <Toolbar>
            <IconButton
              edge="start"
              className={classes.menuButton}
              color="inherit"
              aria-label=""
            >
              <MenuIcon />
            </IconButton>
            <Typography className={classes.title} variant="h6" noWrap>
              <strong>JOBHUB</strong>
            </Typography>
            <div className={classes.grow} />
            <div className={classes.sectionDesktop}>
              <div>
                <h3>Welcome!</h3>  
              </div>
              <SignOutButton />
            </div>
          </Toolbar>
        </AppBar>
      </div>
    );
  }
}

const mapStateToProps = createSelector(
  [ getCurrentUser], (userObject) => ({ userObject}) 
  );

export default connect(mapStateToProps)(withStyles(styles)(ToolbarComponent));
