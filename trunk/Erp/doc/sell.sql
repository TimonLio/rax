--
-- PostgreSQL database dump
--

-- Started on 2009-05-04 20:23:31

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1499 (class 1259 OID 17471)
-- Dependencies: 1776 6
-- Name: sell; Type: TABLE; Schema: public; Owner: erp; Tablespace: 
--

CREATE TABLE sell (
    id integer NOT NULL,
    customer_name character varying(64) NOT NULL,
    customer_address character varying(128) NOT NULL,
    customer_phone1 character varying(64) NOT NULL,
    customer_phone2 character varying(64) NOT NULL,
    customer_postcode character varying(6) NOT NULL,
    fee real NOT NULL,
    fee_real real NOT NULL,
    create_date timestamp without time zone NOT NULL,
    express_id integer NOT NULL,
    sender character varying(8) NOT NULL,
    status integer NOT NULL,
    comment_express character varying(64) NOT NULL,
    comment_invoice character varying(64) NOT NULL,
    send_date date NOT NULL,
    customer_im character varying(64) NOT NULL,
    customer_im_type integer DEFAULT 0 NOT NULL,
    customer_im_comment character varying(64) NOT NULL
);


ALTER TABLE public.sell OWNER TO erp;

--
-- TOC entry 1500 (class 1259 OID 17478)
-- Dependencies: 1499 6
-- Name: sell_id_seq; Type: SEQUENCE; Schema: public; Owner: erp
--

CREATE SEQUENCE sell_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.sell_id_seq OWNER TO erp;

--
-- TOC entry 1784 (class 0 OID 0)
-- Dependencies: 1500
-- Name: sell_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: erp
--

ALTER SEQUENCE sell_id_seq OWNED BY sell.id;


--
-- TOC entry 1777 (class 2604 OID 17511)
-- Dependencies: 1500 1499
-- Name: id; Type: DEFAULT; Schema: public; Owner: erp
--

ALTER TABLE sell ALTER COLUMN id SET DEFAULT nextval('sell_id_seq'::regclass);


--
-- TOC entry 1780 (class 2606 OID 17526)
-- Dependencies: 1499 1499
-- Name: sell_p_key; Type: CONSTRAINT; Schema: public; Owner: erp; Tablespace: 
--

ALTER TABLE ONLY sell
    ADD CONSTRAINT sell_p_key PRIMARY KEY (id);


--
-- TOC entry 1778 (class 1259 OID 17536)
-- Dependencies: 1499
-- Name: sell_date; Type: INDEX; Schema: public; Owner: erp; Tablespace: 
--

CREATE INDEX sell_date ON sell USING btree (create_date);


--
-- TOC entry 1781 (class 1259 OID 17538)
-- Dependencies: 1499
-- Name: sell_status; Type: INDEX; Schema: public; Owner: erp; Tablespace: 
--

CREATE INDEX sell_status ON sell USING btree (status);


-- Completed on 2009-05-04 20:23:31

--
-- PostgreSQL database dump complete
--

