import React from '../../node_modules/react';

function ArrowNavBar(props) {
    return (
        <div>
            <button className="btn btn-secondary mx-1"
                onClick={() => (props.setPage(page => page - 5))}>&#x00AB;</button>
            <button className="btn btn-secondary mx-1"
                onClick={() => (props.setPage(page => page - 1))}>←</button>
            <button className="btn btn-secondary mx-1"
                onClick={() => (props.setPage(page => page + 1))}>→</button>
            <button className="btn btn-secondary mx-1"
                onClick={() => (props.setPage(page => page + 5))}>&#x00BB;</button>
        </div>
    );
}

export default ArrowNavBar;